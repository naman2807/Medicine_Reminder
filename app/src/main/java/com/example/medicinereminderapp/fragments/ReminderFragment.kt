package com.example.medicinereminderapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.FragmentReminderBinding
import com.example.medicinereminderapp.viewmodel.MedicineViewModel
import com.example.medicinereminderapp.viewmodel.MedicineViewModelFactory

class ReminderFragment : Fragment() {
    private var _binding : FragmentReminderBinding? = null
    private val  binding get()  = _binding!!

    private val viewModel: MedicineViewModel by activityViewModels {
        MedicineViewModelFactory(
            (activity?.application as MedicineReminderApplication).database.getMedicineDao())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }
}