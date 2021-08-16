package com.example.medicinereminderapp

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.databinding.ActivityMainBinding
import com.example.medicinereminderapp.fragments.LoginFragment
import com.example.medicinereminderapp.fragments.RegisterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, LoginFragment()).commit()
    }

}