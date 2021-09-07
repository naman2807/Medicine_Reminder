package com.example.medicinereminderapp.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Build.VERSION.SDK
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.medicinereminderapp.MainActivity
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.fragments.*
import com.example.medicinereminderapp.viewmodel.BaseActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(){

//    Add toggle button to open NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var selectedFragment : Fragment


    private val viewModel : BaseActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("login", Context.MODE_PRIVATE)
        val edit: SharedPreferences.Editor? = sharedPreferences.edit()
        Log.e("BaseActivity", sharedPreferences.getString("USER_ID","null").toString())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        //Sets the starting layout to Home page.
        viewModel.selectedFragment.value?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fragment, it).commit()
        }

        //Setting up Navigation Drawer
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.navView)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.pill_schedule -> Toast.makeText(this, "Clicked pill schedule", Toast.LENGTH_LONG).show()
                R.id.setting -> Toast.makeText(this, "Clicked settings", Toast.LENGTH_LONG).show()
                R.id.problem -> Toast.makeText(this, "Clicked problem", Toast.LENGTH_LONG).show()
                R.id.share -> Toast.makeText(this, "Clicked share", Toast.LENGTH_LONG).show()
                R.id.love_it -> Toast.makeText(this, "Clicked love it", Toast.LENGTH_LONG).show()
                R.id.log_out -> {
                    edit?.clear()
                    edit?.apply()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                }
            }
           return@setNavigationItemSelectedListener true
        }

//        Setting up Bottom Navigation Drawer
         val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation)
         val listener = BottomNavigationView.OnNavigationItemSelectedListener {
             when(it.itemId){
                 R.id.bottom_home -> {
                     selectedFragment = HomeFragment()
                     viewModel.selectFragment(selectedFragment)
                 }
                 R.id.reminder -> {
                     selectedFragment = ReminderFragment()
                     viewModel.selectFragment(selectedFragment)
                 }
                 R.id.add_reminder -> {
                     selectedFragment = AddMedicineReminderFragment(bottomNav)
                     viewModel.selectFragment(selectedFragment)
                 }
                 R.id.reports -> {
                     selectedFragment = ReportsFragment()
                     viewModel.selectFragment(selectedFragment)
                 }
                 R.id.assertion -> {
                     selectedFragment = AffirmationFragment()
                     viewModel.selectFragment(selectedFragment)
                 }
             }
             supportFragmentManager.beginTransaction().replace(R.id.fragment, selectedFragment).commit()
             return@OnNavigationItemSelectedListener true
         }
        bottomNav.setOnNavigationItemSelectedListener(listener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}