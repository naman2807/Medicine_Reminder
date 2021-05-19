package com.example.medicinereminderapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.adapter.HomeFragmentAdapter
import com.example.medicinereminderapp.data.HomeDataSource
import com.example.medicinereminderapp.databinding.FragmentHomeBinding
import com.example.medicinereminderapp.model.Categories

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView : RecyclerView = binding.recyclerView
        val dataList : List<Categories> = HomeDataSource().loadItems()
        recyclerView.adapter = HomeFragmentAdapter(requireContext(), dataList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }
}