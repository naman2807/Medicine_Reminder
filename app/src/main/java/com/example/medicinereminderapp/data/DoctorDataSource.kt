package com.example.medicinereminderapp.data

import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Doctor

class DoctorDataSource {

    fun loadDoctors(): List<Doctor>{
        return listOf(Doctor(R.drawable.ic_baseline_tag_faces_24, R.string.naman, R.string.specialist,
                        R.string.description),
                Doctor(R.drawable.ic_baseline_tag_faces_24, R.string.rohan, R.string.specialist, R.string.description),
                Doctor(R.drawable.ic_baseline_tag_faces_24,R.string.himanshu, R.string.specialist, R.string.description),
                Doctor(R.drawable.ic_baseline_tag_faces_24, R.string.fardin, R.string.specialist, R.string.description)
        )
    }
}