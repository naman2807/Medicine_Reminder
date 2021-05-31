package com.example.medicinereminderapp.activity

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.medicinereminderapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

   fun login(view: View){
        val intent = Intent(this, BaseActivity::class.java)
        this.startActivity(intent)
    }

   fun register(view: View){
        val intent = Intent(this, RegisterActivity::class.java)
        this.startActivity(intent)
    }
}