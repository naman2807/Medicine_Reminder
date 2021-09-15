package com.example.medicinereminderapp.data

import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Apps

class AppsDataSource {

    fun loadApps():List<Apps>{
        return listOf(
            Apps(R.drawable.arogya),
            Apps(R.drawable.pharmeasy),
            Apps(R.drawable.one_mg),
            Apps(R.drawable.teladoc),
            Apps(R.drawable.mysugr)
        )
    }
}