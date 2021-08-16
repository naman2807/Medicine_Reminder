package com.example.medicinereminderapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.medicinereminderapp.MedicineReminderApplication
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.RegisterBinding
import com.example.medicinereminderapp.viewmodel.UserViewModel
import com.example.medicinereminderapp.viewmodel.UserViewModelFactory

class RegisterFragment : Fragment() {

    private lateinit var binding: RegisterBinding

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
        binding = RegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.register.setOnClickListener {
            addUser()
        }
    }

    private fun isUserValid(): Boolean {
        return viewModel.isUserValid(
            binding.nameEditText.text.toString(),
            binding.addressEditText.text.toString(),
            binding.phoneEditText.text.toString(),
            binding.emailEditText.text.toString(),
            binding.userEditText.text.toString(),
            binding.passwordRegisterEditText.text.toString()
        )
    }

    private fun addUser() {
        if (isUserValid()) {
            viewModel.addNewUser(
                binding.nameEditText.text.toString(),
                binding.addressEditText.text.toString(),
                binding.phoneEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.userEditText.text.toString(),
                binding.passwordRegisterEditText.text.toString()
            )
            Toast.makeText(requireContext(), "User Added Successfully", Toast.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.mainFragment, LoginFragment())?.commit()
        } else {
            Toast.makeText(requireContext(), "Enter All Details", Toast.LENGTH_SHORT).show()
        }

    }
}