package com.example.medicinereminderapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(){

//    Add toggle button to open NavigationView
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        //Setting up Navigation Drawer
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.navView)
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

//        Setting up Bottom Navigation Drawer
         val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation)
         val listener = BottomNavigationView.OnNavigationItemSelectedListener {
            lateinit var selectedFragment : Fragment
             when(it.itemId){
                 R.id.home -> selectedFragment = Fragment1()
                 R.id.favourite -> selectedFragment = Fragment2()
                 R.id.search -> selectedFragment = Fragment3()
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