package com.example.medicinereminderapp.database.roomdatabase

import androidx.room.Database
import com.example.medicinereminderapp.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MedicineReminderDatabase {
}