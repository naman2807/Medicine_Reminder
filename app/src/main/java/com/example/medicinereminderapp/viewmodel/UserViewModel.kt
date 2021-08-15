package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminderapp.database.dao.UserDao
import com.example.medicinereminderapp.model.User
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao): ViewModel() {

    fun addNewUser(user: User){
        viewModelScope.launch {
            userDao.addNewUser(user)
        }
    }
}