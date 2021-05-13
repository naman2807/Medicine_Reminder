package com.example.medicinereminderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medicinereminderapp.databinding.FragmentAssertionBinding

class AssertionFragmnent : Fragment() {
        private lateinit var binding : FragmentAssertionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssertionBinding.inflate(inflater, container, false)
        return binding.root
    }
}