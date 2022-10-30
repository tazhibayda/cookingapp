package com.example.cookingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cookingapp.databinding.HomePageBinding

class HomePageActivity : AppCompatActivity() {

    private lateinit var homePagebinding: HomePageBinding

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homePagebinding = HomePageBinding.inflate(layoutInflater)
        setContentView(homePagebinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        homePagebinding.bottomNavigationView.setupWithNavController(navController)
    }
}