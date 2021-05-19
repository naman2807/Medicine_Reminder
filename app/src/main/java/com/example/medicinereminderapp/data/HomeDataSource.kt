package com.example.medicinereminderapp.data

import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Categories

class HomeDataSource {

    fun loadItems() : List<Categories>{
        return listOf<Categories>(
                Categories(R.drawable.ic_cardiology, R.string.cardiologist, R.string.specialist)
        )
    }
}