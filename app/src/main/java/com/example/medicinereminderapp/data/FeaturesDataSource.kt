package com.example.medicinereminderapp.data

import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Features

class FeaturesDataSource {
    fun loadFeatures(): List<Features> {
        return listOf(
            Features(R.drawable.vaccine_finder),
            Features(R.drawable.vaccine_verification),
            Features(R.drawable.health_ministry),
            Features(R.drawable.medicine_buyer),
            Features(R.drawable.cghs)
        )
    }
}