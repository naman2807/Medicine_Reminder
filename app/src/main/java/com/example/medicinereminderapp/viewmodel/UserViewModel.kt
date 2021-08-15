package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.medicinereminderapp.database.dao.UserDao

class UserViewModel(private val userDao: UserDao): ViewModel() {
}