package com.example.medicinereminderapp

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.activity.BaseActivity
import com.example.medicinereminderapp.databinding.ActivityMainBinding
import com.example.medicinereminderapp.fragments.LoginFragment
import com.example.medicinereminderapp.fragments.RegisterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Checks if user has logged in or not. If logged in, then show home screen.
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        val user = sharedPreferences.getString("USER_ID", "null")
        if(!user.toString().equals("null")){
            val intent = Intent(this, BaseActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // If user is logged out, then show login page
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, LoginFragment()).commit()
    }

}