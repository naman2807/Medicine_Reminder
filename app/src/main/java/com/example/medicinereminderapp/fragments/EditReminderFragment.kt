package com.example.medicinereminderapp.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.EditReminderBinding
import com.example.medicinereminderapp.model.Medicine
import com.example.medicinereminderapp.viewmodel.MedicineViewModel
import com.example.medicinereminderapp.viewmodel.MedicineViewModelFactory
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
        binding.fromDateInputText.setOnClickListener{
            DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
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
            TimePickerDialog(
                requireContext(),
                { view, hourOfDay, minuteOfDay ->
                    binding.timeInputText.setText("$hourOfDay : $minuteOfDay")
                },
                hour,
                minute,
                false
            ).apply {
                setTitle("Set Medicine Time")
                show()
            }
        }

        binding.submit.setOnClickListener {
            val fromDate : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val frDate = fromDate.parse(binding.fromDateInputText.text.toString())
            val toDate : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val tDate = toDate.parse(binding.toDateInputText.text.toString())
            if(tDate.before(frDate)){
                binding.toDateInputLayout.error = "Enter Valid Date"
            }else{
                updateMedicine()
                Toast.makeText(requireContext(), "Medicine: ${medicine.name} Updated Successfully", Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment, ReminderFragment())?.commit()
            }
        }
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
}