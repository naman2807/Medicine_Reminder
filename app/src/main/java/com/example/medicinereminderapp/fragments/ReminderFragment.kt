package com.example.medicinereminderapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.FragmentReminderBinding

class ReminderFragment : Fragment() {
    private var _binding : FragmentReminderBinding? = null
    private val  binding get()  = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }
}