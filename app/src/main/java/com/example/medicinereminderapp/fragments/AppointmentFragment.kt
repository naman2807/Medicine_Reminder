package com.example.medicinereminderapp.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.FragmentAppointmentBinding
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class AppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.fromDateInputText.setOnClickListener{
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                Toast.makeText(requireContext(), "" +  " " + monthOfYear + ", " + year, Toast.LENGTH_SHORT).show()

            }, year, month, day)

            dpd.show()
        }
    }

    private fun getMonth(number: Int): String{

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