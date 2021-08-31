package com.example.medicinereminderapp.database.dao

import androidx.room.*
import com.example.medicinereminderapp.model.Medicine
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE )
    suspend fun addMedicine(medicine: Medicine)

    @Update
    suspend fun updateMedicine(medicine: Medicine)

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Query("SELECT * FROM medicine_table")
    fun getAllMedicines(): Flow<Medicine>
}