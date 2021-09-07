package com.example.medicinereminderapp.fragments

import android.app.*
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.FragmentAppointmentBinding
import com.example.medicinereminderapp.notification.AlarmReceiver
import com.example.medicinereminderapp.viewmodel.MedicineViewModel
import com.example.medicinereminderapp.viewmodel.MedicineViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class AddMedicineReminderFragment(val bottomNavigationView: BottomNavigationView) : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)
    private lateinit var user: String
    private lateinit var sharedPreferences: SharedPreferences
    private val CHANNEL_ID = "medicineReminder"
    private lateinit var timePicker: MaterialTimePicker
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    private val viewModel: MedicineViewModel by activityViewModels {
        MedicineViewModelFactory(
            (activity?.application as MedicineReminderApplication).database.getMedicineDao()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPreferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        user = sharedPreferences.getString("USER_ID","null")!!
        createNotificationChannel()

        binding.fromDateInputText.setOnClickListener{
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

        binding.toDateInputText.setOnClickListener{
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

        binding.timeInputText.setOnClickListener{
//            TimePickerDialog(
//                requireContext(),
//                { view, hourOfDay, minuteOfDay ->
//                binding.timeInputText.setText("$hourOfDay : $minuteOfDay")
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
            val fromDate : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val frDate = fromDate.parse(binding.fromDateInputText.text.toString())
            val toDate : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val tDate = toDate.parse(binding.toDateInputText.text.toString())
            if(tDate.before(frDate)){
                binding.toDateInputLayout.error = "Enter Valid Date"
            }else{
                addNewMedicine()
            }
        }
    }

    private fun isAnyFieldEmpty(): Boolean{
        return viewModel.isAnyFieldEmpty(
            binding.medicineInputText.text.toString(),
            binding.doctorInputText.text.toString(),
            binding.fromDateInputText.text.toString(),
            binding.toDateInputText.text.toString(),
            binding.timeInputText.text.toString()
        )
    }

    private fun addNewMedicine(){
        if(!isAnyFieldEmpty()){
            viewModel.addMedicine(
            user,
            binding.medicineInputText.text.toString(),
            binding.doctorInputText.text.toString(),
            binding.fromDateInputText.text.toString(),
            binding.toDateInputText.text.toString(),
            binding.timeInputText.text.toString()
        )
            setAlarm()
            Toast.makeText(requireContext(),"Reminder Added Successfully",Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, ReminderFragment())?.commit()
            bottomNavigationView.selectedItemId = R.id.reminder
        }else{
            binding.medicineInputLayout.error = "Empty Field"
            binding.doctorInputLayout.error = "Empty Field"
            binding.fromDateInputLayout.error = "Empty Field"
            binding.toDateInputLayout.error = "Empty Field"
            binding.timeInputLayout.error = "Empty Field"
        }
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "medicineReminderChannel"
            val description = "Alarm Manager for Medicine Reminder App"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description

            val notificationManager = getSystemService(requireContext(),
                NotificationManager::class.java
            )

            notificationManager?.createNotificationChannel(channel)
        }
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

    private fun setAlarm(){
        alarmManager = activity?.getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            c.timeInMillis,
            pendingIntent
        )
    }

    private fun getMonth(number: Int): String{
        val monthNumber = number + 1
        var monthToDisplay: String = ""
        when(monthNumber){
            1 -> monthToDisplay = Months.ONE.month
            2 -> monthToDisplay = Months.TWO.month
            3 -> monthToDisplay = Months.THREE.month
            4 -> monthToDisplay = Months.FOUR.month
            5 -> monthToDisplay = Months.FIVE.month
            6 -> monthToDisplay = Months.SIX.month
            7 -> monthToDisplay = Months.SEVEN.month
            8 -> monthToDisplay = Months.EIGHT.month
            9 -> monthToDisplay = Months.NINE.month
            10 -> monthToDisplay = Months.TEN.month
            11 -> monthToDisplay = Months.ELEVEN.month
            12 -> monthToDisplay = Months.TWELVE.month
        }
        return monthToDisplay
    }

    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }

    override fun onResume() {
        super.onResume()
        binding.medicineInputLayout.error = null
        binding.doctorInputLayout.error = null
        binding.fromDateInputLayout.error = null
        binding.toDateInputLayout.error = null
        binding.timeInputLayout.error = null
    }

    companion object{
        enum class Months(val month: String){
            ONE("JANUARY"),
            TWO("FEBRUARY"),
            THREE("MARCH"),
            FOUR("APRIL"),
            FIVE("MAY"),
            SIX("JUNE"),
            SEVEN("JULY"),
            EIGHT("AUGUST"),
            NINE("SEPTEMBER"),
            TEN("OCTOBER"),
            ELEVEN("NOVEMBER"),
            TWELVE("DECEMBER")
        }
    }

}