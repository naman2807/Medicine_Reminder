package com.example.medicinereminderapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.databinding.SettingsFragmentBinding
import com.example.medicinereminderapp.model.User
import com.example.medicinereminderapp.viewmodel.UserViewModel
import com.example.medicinereminderapp.viewmodel.UserViewModelFactory

class SettingsFragment : Fragment() {
    private lateinit var binding: SettingsFragmentBinding
    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory((activity?.application as MedicineReminderApplication).database.getUserDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("USER_ID", "null").toString()
        binding.userProfileValue.text = userId
        val user: LiveData<User> = userViewModel.findUser(userId)
        user.observe(viewLifecycleOwner, { e ->
            binding.emailProfileValue.text = e.userEmail
            binding.nameProfileValue.text = e.userName
            binding.addressProfileValue.text = e.userAddress
            binding.phoneProfileValue.text = e.userPhoneNumber

        })
    }
}