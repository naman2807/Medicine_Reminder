package com.example.medicinereminderapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.activity.BaseActivity
import com.example.medicinereminderapp.databinding.LoginBinding
import com.example.medicinereminderapp.databinding.RegisterBinding
import com.example.medicinereminderapp.viewmodel.UserViewModel
import com.example.medicinereminderapp.viewmodel.UserViewModelFactory

class LoginFragment: Fragment() {
    private lateinit var binding: LoginBinding


    private val viewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(
            (activity?.application as MedicineReminderApplication).database.getUserDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signup.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.mainFragment, RegisterFragment())?.commit()
        }

        binding.loginBtn.setOnClickListener {
            val intent = Intent(requireContext(), BaseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateUser(){
        val userId = binding.userIdEditText.text.toString()
        val password = binding.userIdPasswordText.text.toString()

        val user = viewModel.findUser(userId)
        if(user.value != null){
            if(user.value?.userPassword.equals(password)){

            }
        }
    }

}