package com.example.medicinereminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "name")
    val userName: String,
    @ColumnInfo(name = "address")
    val userAddress: String,
    @ColumnInfo(name = "phone_number")
    val userPhoneNumber: String,
    @ColumnInfo(name = "email")
    val userEmail: String,
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "password")
    val userPassword: String
)