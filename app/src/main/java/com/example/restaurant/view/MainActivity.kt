package com.example.restaurant.view


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.restaurant.R
import com.example.restaurant.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            bottomNavigation()
            toolbar()


    }


    private fun toolbar() {
        setSupportActionBar(binding.toolbar)

        //disable night mood
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun bottomNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment?
        val navController = navHostFragment!!.navController
        val navView: BottomNavigationView = binding.bottomNavigationView
        navView.setupWithNavController(navController)

        binding.bottomNavigationView.selectedItemId = R.id.orderFragment
        binding.toolbar.title = "رستوران"
        binding.toolbar.setTitleTextColor(Color.WHITE)


        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this ,
            binding.drawerLayout ,
            binding.toolbar ,
            R.string.openDrawer ,
            R.string.closeDrawer
        )

        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


    }
}