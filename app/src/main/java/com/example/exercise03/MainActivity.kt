package com.example.exercise03

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.menu.MenuBuilder
import androidx.navigation.fragment.NavHostFragment
import com.example.exercise03.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_container2) as NavHostFragment
        val navController =navHostFragment.navController
        val bottomNavView: BottomNavigationView = binding.bottomNavigationView

        bottomNavView.setOnItemSelectedListener {
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

        /*val appBarConfig: AppBarConfiguration =
            AppBarConfiguration(
                setOf(R.id.fragmentLeft, R.id.fragmentCenter, R.id.fragmentRight))
        setupActionBarWithNavController(navController, appBarConfig)
        bottomNavView.setupWithNavController(navController)*/
    }

    private fun setPrefs(themeNum: Int) {
        val data: SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        val editor = data.edit()
        editor.putInt("theme_num", themeNum)
        editor.apply()
    }

    private fun applyTheme() {
        val data = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val themeNum = data.getInt("theme_num", 0)
        when (themeNum) {
            1 -> setTheme(R.style.AppTheme1)
            2 -> setTheme(R.style.AppTheme2)
            3 -> setTheme(R.style.AppTheme3)
            else -> setTheme(R.style.Theme_Exercise03)
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)

            val btnThemeDefault = menu.findItem(R.id.mi_id_4)
            btnThemeDefault.setIcon(R.drawable.restore_icon)

            val btnTheme1 = menu.findItem(R.id.mi_id_1)
            btnTheme1.setIcon(R.drawable.theme1_icon)

            val btnTheme2 = menu.findItem(R.id.mi_id_2)
            btnTheme2.setIcon(R.drawable.theme2_icon)

            val btnTheme3 = menu.findItem(R.id.mi_id_3)
            btnTheme3.setIcon(R.drawable.theme3_icon)
        }

        return true
    }

    //create menu as defined in menu.xml
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_id_1 -> {
                setPrefs(1)
                recreate()
                true
            }
            R.id.mi_id_2 -> {
                setPrefs(2)
                recreate()
                true
            }
            R.id.mi_id_3 -> {
                setPrefs(3)
                recreate()
                true
            }
            R.id.mi_id_4 -> {
                //default theme
                setPrefs(0)
                recreate()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}