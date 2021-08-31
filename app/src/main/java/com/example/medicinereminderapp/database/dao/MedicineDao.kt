package com.example.medicinereminderapp.database.dao

import androidx.room.*
import com.example.medicinereminderapp.model.Medicine

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE )
    suspend fun addMedicine(medicine: Medicine)

    @Update
    suspend fun updateMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)
}