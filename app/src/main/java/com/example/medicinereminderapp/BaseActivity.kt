package com.example.medicinereminderapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(){

//    Add toggle button to open NavigationView
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        var navView : NavigationView = findViewById(R.id.navView)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> Toast.makeText(this, "Clicked Item1", Toast.LENGTH_LONG).show()
                R.id.item2 -> Toast.makeText(this, "Clicked Item2", Toast.LENGTH_LONG).show()
                R.id.item3 -> Toast.makeText(this, "Clicked Item3", Toast.LENGTH_LONG).show()
                R.id.item4 -> Toast.makeText(this, "Clicked Item4", Toast.LENGTH_LONG).show()
                R.id.item5 -> Toast.makeText(this, "Clicked Item5", Toast.LENGTH_LONG).show()
            }
           return@setNavigationItemSelectedListener true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}