package com.example.medicinereminderapp.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NavUtils
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
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

    private var sharedPreferences: SharedPreferences? = null

    private var editor: SharedPreferences.Editor? = null

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

        // Inflated Login page.
        binding = LoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Create sharedPreference file and its editor for first time. This file is created once after the application is installed
        // in mobile. If already present, then returns the instance of previously created file.
        sharedPreferences = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()

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
                editor?.putString(TAG, user.userId)
                editor?.apply()
                startNewActivity()
            } else {
                binding.userIdPasswordLayout.error = getString(R.string.incorrectPassword)
                binding.userIdTextLayout.error = getString(R.string.incorrectId)
            }
        })
    }

    private fun startNewActivity(){
        val intent = Intent(requireContext(), BaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }
}