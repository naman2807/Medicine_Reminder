package com.example.medicinereminderapp.database.dao

import androidx.room.*
import com.example.medicinereminderapp.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun addNewUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user WHERE user_id = :userId")
    fun getUser(userId: String): Flow<User>
}