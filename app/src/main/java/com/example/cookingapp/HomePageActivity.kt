package com.example.cookingapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.cookingapp.databinding.HomePageBinding
import com.example.cookingapp.models.CookNowViewModel

class HomePageActivity : AppCompatActivity() {

    private lateinit var homePagebinding: HomePageBinding
    private val cookNowViewModel: CookNowViewModel by viewModels()
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
                R.id.loginFragment,
                R.id.homeFragment
                -> false
                else -> true
            }
        }


    }
}