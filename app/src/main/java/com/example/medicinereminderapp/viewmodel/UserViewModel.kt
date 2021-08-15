package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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
        return name.isBlank() || address.isBlank() || phoneNumber.isBlank() || email.isBlank()
                || userId.isBlank() || password.isBlank()
    }

    fun isUserExists(userId: String): Boolean{
        val user = userDao.getUser(userId).asLiveData()
        return user.value == null
    }

    fun addNewUser(name: String, address: String, phoneNumber: String, email:String,
                   userId: String, password: String){

        addNewUser(User(name, address, phoneNumber, email, userId, password))
    }
}