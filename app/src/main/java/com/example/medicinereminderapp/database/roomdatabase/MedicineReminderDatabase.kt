package com.example.medicinereminderapp.database.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medicinereminderapp.database.dao.UserDao
import com.example.medicinereminderapp.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MedicineReminderDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: MedicineReminderDatabase? = null

        fun getDatabase(context: Context): MedicineReminderDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicineReminderDatabase::class.java,
                    "medicine_reminder_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}