package com.example.medicinereminderapp.data

import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Insurance

class InsuranceDataSource {
    fun loadInsurances():List<Insurance>{
        return listOf(
            Insurance(R.drawable.life_insurance),
            Insurance(R.drawable.health_insurance),
            Insurance(R.drawable.child_insurance),
            Insurance(R.drawable.home_insurance),
            Insurance(R.drawable.family_insurance)
        )
    }
}