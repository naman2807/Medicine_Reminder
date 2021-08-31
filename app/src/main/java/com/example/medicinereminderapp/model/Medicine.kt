package com.example.medicinereminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
data class Medicine(
    @PrimaryKey
    @ColumnInfo(name = "medicine_name")
    val name: String,
    @ColumnInfo(name = "doctor_name")
    val doctorName: String)