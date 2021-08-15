package com.example.medicinereminderapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.medicinereminderapp.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun addNewUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}