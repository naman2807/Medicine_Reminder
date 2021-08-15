package com.example.medicinereminderapp

import android.app.Application
import com.example.medicinereminderapp.database.roomdatabase.MedicineReminderDatabase

class MedicineReminderApplication:Application() {
    val database : MedicineReminderDatabase by lazy {
        MedicineReminderDatabase.getDatabase(this)
    }
}