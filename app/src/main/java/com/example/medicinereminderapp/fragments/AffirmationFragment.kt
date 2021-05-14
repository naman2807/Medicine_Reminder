package com.example.medicinereminderapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.adapter.AffirmationFragmentAdapter
import com.example.medicinereminderapp.data.DataSource
import com.example.medicinereminderapp.databinding.FragmentAssertionBinding
import com.example.medicinereminderapp.model.Affirmation

class AffirmationFragment : Fragment() {
        private lateinit var binding : FragmentAssertionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAssertionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerview : RecyclerView = binding.assertionView
        val dataList : List<Affirmation> = DataSource().loadAffirmation()
        recyclerview.adapter = AffirmationFragmentAdapter(requireContext(), dataList)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.setHasFixedSize(true)
    }

    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }
}