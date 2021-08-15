package com.example.medicinereminderapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.medicinereminderapp.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun addNewUser(user: User)
}