package com.example.medicinereminderapp.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.FragmentAppointmentBinding
import com.example.medicinereminderapp.viewmodel.MedicineViewModel
import com.example.medicinereminderapp.viewmodel.MedicineViewModelFactory
import com.example.medicinereminderapp.viewmodel.UserViewModel
import com.example.medicinereminderapp.viewmodel.UserViewModelFactory
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)
    private lateinit var user: String

    private val viewModel: MedicineViewModel by activityViewModels {
        MedicineViewModelFactory(
            (activity?.application as MedicineReminderApplication).database.getMedicineDao()
        )
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPreferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        user = sharedPreferences.getString("USER_ID","null")!!
        Log.e("Appointment", user)

        binding.fromDateInputText.setOnClickListener{
           DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    binding.fromDateInputText.setText("${getMonth(monthOfYear)} $dayOfMonth, $year")
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
                   binding.toDateInputText.setText("${getMonth(monthOfYear)} $dayOfMonth, $year")
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

        binding.submit.setOnClickListener { addNewMedicine() }
    }

    private fun isAnyFieldEmpty(){

    }
    private fun addNewMedicine(){
        if(!viewModel.isAnyFieldEmpty(
                binding.medicineInputText.text.toString(),
                binding.doctorInputText.text.toString(),
                binding.fromDateInputText.text.toString(),
                binding.toDateInputText.text.toString(),
                binding.timeInputText.text.toString()
        )){
            viewModel.addMedicine(
            user,
            binding.medicineInputText.text.toString(),
            binding.doctorInputText.text.toString(),
            binding.fromDateInputText.text.toString(),
            binding.toDateInputText.text.toString(),
            binding.timeInputText.text.toString()
        )
        }
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