package com.example.medicinereminderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ReminderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_reminder, container, false)
        return layout
    }

    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }
}