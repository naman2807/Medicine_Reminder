package com.example.medicinereminderapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.adapter.*
import com.example.medicinereminderapp.data.*
import com.example.medicinereminderapp.databinding.FragmentHome1Binding
import com.example.medicinereminderapp.databinding.FragmentHomeBinding
import com.example.medicinereminderapp.model.Categories
import com.example.medicinereminderapp.model.Doctor
import com.example.medicinereminderapp.model.Features

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHome1Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHome1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val featuresRecyclerView : RecyclerView = binding.featuresRecyclerView
        val featureList : List<Features> = FeaturesDataSource().loadFeatures()
        featuresRecyclerView.adapter = FeatureAdapter(requireContext(), featureList)
        featuresRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        featuresRecyclerView.setHasFixedSize(true)

        val directionsRecyclerView = binding.pathsRecyclerView
        val directionsList = DirectionsDataSource().loadDirections()
        directionsRecyclerView.adapter = DirectionsAdapter(requireContext(), directionsList)
        directionsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        directionsRecyclerView.setHasFixedSize(true)

        val insuranceRecyclerView = binding.insuranceRecyclerView
        val insuranceList = InsuranceDataSource().loadInsurances()
        insuranceRecyclerView.adapter = InsuranceAdapter(requireContext(), insuranceList)
        insuranceRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        insuranceRecyclerView.setHasFixedSize(true)

        val appRecyclerView = binding.miniAppRecyclerView
        val appList = AppsDataSource().loadApps()
        appRecyclerView.adapter = AppsAdapter(requireContext(), appList)
        appRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        appRecyclerView.setHasFixedSize(true)

        binding.searchCovid.setOnClickListener {
            openCovidNetwork()
        }

    }

    private fun openCovidNetwork(){
        startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.mygov.in/covid-19")), null)
    }

    override fun toString(): String {
        return activity?.javaClass?.simpleName!!
    }
}