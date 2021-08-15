package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.medicinereminderapp.database.dao.UserDao
import com.example.medicinereminderapp.model.User

class UserViewModel(private val userDao: UserDao): ViewModel() {
    fun addNewUser(user: User){

    }
}