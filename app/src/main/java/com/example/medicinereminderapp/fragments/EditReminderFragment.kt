package com.example.medicinereminderapp.fragments

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.EditReminderBinding
import com.example.medicinereminderapp.model.Medicine
import com.example.medicinereminderapp.notification.AlarmReceiver
import com.example.medicinereminderapp.viewmodel.MedicineViewModel
import com.example.medicinereminderapp.viewmodel.MedicineViewModelFactory
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class EditReminderFragment(val medicine: Medicine) : Fragment() {
    private lateinit var binding: EditReminderBinding
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)
    private val CHANNEL_ID = "medicineReminder"
    private lateinit var timePicker: MaterialTimePicker
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    val viewModel: MedicineViewModel by activityViewModels {
        MedicineViewModelFactory(
            (activity?.application as MedicineReminderApplication).database.getMedicineDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setCalenderAndTime()
        binding.medicineInputLayout.isEnabled = false
        setValues(medicine)
    }

    private fun setCalenderAndTime() {
        binding.fromDateInputText.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    c[Calendar.MONTH] = monthOfYear
                    c[Calendar.DATE] = dayOfMonth
                    c[Calendar.YEAR] = year
                    binding.fromDateInputText.setText("$dayOfMonth/$monthOfYear/$year")
                },
                year,
                month,
                day
            ).apply {
                show()
            }
        }

        binding.toDateInputText.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.toDateInputText.setText("$dayOfMonth/$monthOfYear/$year")
                },
                year,
                month,
                day
            ).apply {
                show()
            }
        }

        binding.timeInputText.setOnClickListener {
//            TimePickerDialog(
//                requireContext(),
//                { view, hourOfDay, minuteOfDay ->
//                    binding.timeInputText.setText("$hourOfDay : $minuteOfDay")
//                },
//                hour,
//                minute,
//                false
//            ).apply {
//                setTitle("Set Medicine Time")
//                show()
//            }
            showTimePicker()
        }

        binding.submit.setOnClickListener {
            val fromDate: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val frDate = fromDate.parse(binding.fromDateInputText.text.toString())
            val toDate: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val tDate = toDate.parse(binding.toDateInputText.text.toString())
            if (tDate.before(frDate)) {
                binding.toDateInputLayout.error = "Enter Valid Date"
            } else {
                updateMedicine()
                setAlarm()
                Toast.makeText(
                    requireContext(),
                    "Medicine: ${medicine.name} Updated Successfully",
                    Toast.LENGTH_SHORT
                ).show()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment, ReminderFragment())?.commit()
            }
        }

        binding.delete.setOnClickListener {
            deleteReminder(medicine)
        }
    }

    private fun deleteReminder(medicine: Medicine){
        viewModel.deleteMedicine(medicine)
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, HomeFragment())?.commit()
        Toast.makeText(requireContext(), medicine.name + " deleted successfully", Toast.LENGTH_SHORT).show()
    }

    private fun updateMedicine() {
        if (!isAnyFieldEmpty()) {
            val medicine = medicine.copy(
                name = binding.medicineInputText.text.toString(),
                doctorName = binding.doctorInputText.text.toString(),
                fromDate = binding.fromDateInputText.text.toString(),
                toDate = binding.toDateInputText.text.toString(),
                time = binding.timeInputText.text.toString()
            )

            viewModel.updateMedicine(medicine)
        }
    }

    private fun isAnyFieldEmpty(): Boolean {
        return viewModel.isAnyFieldEmpty(
            binding.medicineInputText.text.toString(),
            binding.doctorInputText.text.toString(),
            binding.fromDateInputText.text.toString(),
            binding.toDateInputText.text.toString(),
            binding.timeInputText.text.toString()
        )
    }

    private fun setValues(medicine: Medicine) {
        binding.medicineInputText.setText(medicine.name)
        binding.doctorInputText.setText(medicine.doctorName)
        binding.fromDateInputText.setText(medicine.fromDate)
        binding.toDateInputText.setText(medicine.toDate)
        binding.timeInputText.setText(medicine.time)
    }

    private fun showTimePicker(){
        timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(hour)
            .setMinute(minute)
            .setTitleText("Set Alarm Time")
            .build()

        timePicker.show(activity?.supportFragmentManager!!, CHANNEL_ID)

        timePicker.addOnPositiveButtonClickListener {
            if(timePicker.hour > 12){
                binding.timeInputText.setText(
                    String.format("%02d", timePicker.hour - 12) + ":" + String.format(
                        "%02d", timePicker.minute
                    ) + " PM"
                )
            }else {
                binding.timeInputText.setText(
                    String.format("%02d", timePicker.hour) + ":" + String.format(
                        "%02d", timePicker.minute
                    ) + " AM"
                )
            }

            c[Calendar.HOUR_OF_DAY] = timePicker.hour
            c[Calendar.MINUTE] = timePicker.minute
            c[Calendar.SECOND] = 0
            c[Calendar.MILLISECOND] = 0

        }
    }

    private fun setAlarm() {
        alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            c.timeInMillis,
            pendingIntent
        )
    }
}