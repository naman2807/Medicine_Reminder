package com.example.medicinereminderapp.data

import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Directions

class DirectionsDataSource {
    fun loadDirections():List<Directions>{
        return listOf(
            Directions(R.drawable.google_map),
            Directions(R.drawable.hospitals),
            Directions(R.drawable.medicine_shop),
            Directions(R.drawable.health_centre)
        )
    }
}