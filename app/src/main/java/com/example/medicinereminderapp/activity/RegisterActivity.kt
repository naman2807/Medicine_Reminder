package com.example.medicinereminderapp.activity

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.medicinereminderapp.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val animation : AnimationDrawable = findViewById<ConstraintLayout>(R.id.constraint_reg).background as AnimationDrawable
        animation.setEnterFadeDuration(1500)
        animation.setExitFadeDuration(3000)
        animation.start()
    }
}