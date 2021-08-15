package com.example.medicinereminderapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.RegisterBinding
import com.example.medicinereminderapp.viewmodel.UserViewModel
import com.example.medicinereminderapp.viewmodel.UserViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(
            (application as MedicineReminderApplication).database.getUserDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val binding = RegisterBinding.inflate(layoutInflater)
        val name = binding.nameEditText.text.toString()
        val address = binding.addressEditText.text.toString()
        val phoneNumber = binding.phoneEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val userId = binding.userEditText.text.toString()
        val password = binding.passwordRegisterEditText.text.toString()


    }

    private fun addUser(name: String, address: String, phoneNumber: String, email: String,
                userId: String, password: String){
        if (!viewModel.isUserValid(name, address, phoneNumber, email, userId, password)) {
            if (!viewModel.isUserExists(userId)) {
                viewModel.addNewUser(name, address, phoneNumber, email, userId, password)
                Toast.makeText(this, "User Added Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User Already Exists", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Enter All Details", Toast.LENGTH_SHORT).show()
        }
    }
}