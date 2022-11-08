package com.example.cookingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.cookingapp.databinding.HomePageBinding

class HomePageActivity : AppCompatActivity() {

    private lateinit var homePagebinding: HomePageBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homePagebinding = HomePageBinding.inflate(layoutInflater)
        setContentView(homePagebinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentMyNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        homePagebinding.bottomNavigationView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            homePagebinding.bottomNavigationView.isVisible = when (destination.id) {
                R.id.homeFragment,R.id.animationFragment -> false
                else -> true
            }
        }


    }
}