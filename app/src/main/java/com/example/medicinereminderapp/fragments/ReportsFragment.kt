package com.example.medicinereminderapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.viewmodel.UserViewModel
import com.example.medicinereminderapp.viewmodel.UserViewModelFactory

class ReportsFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(
            (activity?.application as MedicineReminderApplication).database.getUserDao()
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_reports, container, false)
        return layout
    }

    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }
}