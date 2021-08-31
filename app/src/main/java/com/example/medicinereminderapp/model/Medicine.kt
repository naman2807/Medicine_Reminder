package com.example.medicinereminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_table")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "medicine_id")
    val id: Int,
    @ColumnInfo(name = "medicine_name")
    val name: String)