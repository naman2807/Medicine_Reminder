package com.example.medicinereminderapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.medicinereminderapp.model.Medicine

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE )
    suspend fun addMedicine(medicine: Medicine)
}