package com.example.medicinereminderapp.model

import androidx.room.Entity

@Entity(tableName = "user")
data class User(
    val name: String,
    val address: String,
    val phoneNumber: String,
    val email: String,
    val userId: String,
    val password: String
)