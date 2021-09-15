package com.example.medicinereminderapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.medicinereminderapp.databinding.ErrorFragmentBinding

class ProblemFragment : Fragment() {
    private lateinit var binding: ErrorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ErrorFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.send.setOnClickListener {
            sendProblem()
        }
    }

    private fun sendProblem(){
        val problemIntent = Intent(Intent.ACTION_SEND)
        problemIntent.data = Uri.parse("mailto:")
        problemIntent.type = "text/plain"
        problemIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("naman.agarwal_cs19@gla.ac.in"))
        problemIntent.putExtra(Intent.EXTRA_SUBJECT, "Medicine Reminder App Problem")
        problemIntent.putExtra(Intent.EXTRA_TEXT, binding.problem.text.toString())
        startActivity(Intent.createChooser(problemIntent, "Choose Email"))
    }
}