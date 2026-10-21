package com.example.bitfit
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation =
            findViewById<BottomNavigationView>(
                R.id.bottomNavigation
            )

        // Show Food Log when the app first opens
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    FoodLogFragment()
                )
                .commit()
        }

        // Handle bottom navigation clicks
        bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.nav_log -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            FoodLogFragment()
                        )
                        .commit()
                    true
                }

                R.id.nav_dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            DashboardFragment()
                        )
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}