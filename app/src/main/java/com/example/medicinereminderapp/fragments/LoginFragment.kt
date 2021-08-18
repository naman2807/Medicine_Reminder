package com.example.medicinereminderapp.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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

    private val sharedPreferences: SharedPreferences? =
        activity?.getSharedPreferences("userid", Context.MODE_PRIVATE)

    private val edit: SharedPreferences.Editor? = sharedPreferences?.edit()

    private val TAG = "USER_ID"

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
        val userId = sharedPreferences?.getString(TAG, null)
        if(userId == null){

        }
        binding.signup.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.mainFragment, RegisterFragment())?.commit()
        }

        binding.loginBtn.setOnClickListener {
            validateUser()
        }

    }

    override fun onResume() {
        super.onResume()
        binding.userIdPasswordLayout.error = null
        binding.userIdTextLayout.error = null
        binding.userIdEditText.text = null
        binding.userIdPasswordText.text = null
    }

    private fun validateUser() {
        val userId = binding.userIdEditText.text.toString()
        val password = binding.userIdPasswordText.text.toString()
        val user = viewModel.findUser(userId)
        user.observe(viewLifecycleOwner, { user ->
            if (user?.userPassword.equals(password)) {
                edit?.putString(TAG, user.userId)
                val intent = Intent(requireContext(), BaseActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
            } else {
                binding.userIdPasswordLayout.error = getString(R.string.incorrectPassword)
                binding.userIdTextLayout.error = getString(R.string.incorrectId)
            }
        })
    }

    private fun startNewActivity(){

    }

}