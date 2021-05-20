package com.example.medicinereminderapp.data

import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Categories

class HomeDataSource {

    fun loadItems() : List<Categories>{
        return listOf<Categories>(
                Categories(R.drawable.ic_cardiology, R.string.cardiologist, R.string.specialist),
                Categories(R.drawable.ic_baseline_remove_red_eye_24, R.string.eye, R.string.specialist),
                Categories(R.drawable.ic_polio, R.string.polio, R.string.specialist),
                Categories(R.drawable.ic_vaccine, R.string.vaccine, R.string.specialist),
        )
    }
}