package com.example.exercise03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.exercise03.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_container2) as NavHostFragment
        val navController =navHostFragment.navController
        val bottomNavMenu: BottomNavigationView = binding.bottomNavigationView

        bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragmentLeft -> {
                    navController.navigate(R.id.action_global_to_fragLeft)
                    true
                }
                R.id.fragmentCenter -> {
                    navController.navigate(R.id.action_global_to_fragCenter)
                    true
                }
                R.id.fragmentRight -> {
                    navController.navigate(R.id.action_global_to_fragRight)
                    true
                }
                else -> false
            }
        }
    }
}