package com.example.medicinereminderapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(){

//    Add toggle button to open NavigationView
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        //Sets the starting layout to Home page.
        supportFragmentManager.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()

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
            }
           return@setNavigationItemSelectedListener true
        }

//        Setting up Bottom Navigation Drawer
         val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation)
         val listener = BottomNavigationView.OnNavigationItemSelectedListener {
            lateinit var selectedFragment : Fragment
             when(it.itemId){
                 R.id.bottom_home -> selectedFragment = HomeFragment()
                 R.id.reminder -> selectedFragment = ReminderFragment()
                 R.id.appointment -> selectedFragment = AppointmentFragment()
                 R.id.reports -> selectedFragment = ReportsFragment()
                 R.id.assertion -> selectedFragment = AffirmationFragment()
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

}