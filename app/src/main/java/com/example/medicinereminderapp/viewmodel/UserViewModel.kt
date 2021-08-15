package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminderapp.database.dao.UserDao
import com.example.medicinereminderapp.model.User
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao): ViewModel() {

    private fun addNewUser(user: User){
        viewModelScope.launch {
            userDao.addNewUser(user)
        }
    }

    fun isUserValid(name: String, address: String, phoneNumber: String, email:String,
                userId: String, password: String): Boolean{

    }
}