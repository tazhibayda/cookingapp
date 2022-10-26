package com.example.cookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cookingapp.adapters.RecipeAdapter
import com.example.cookingapp.databinding.ActivityMainBinding
import com.example.cookingapp.databinding.FragmentRecipesBinding
import com.example.cookingapp.databinding.HomePageBinding
import com.example.cookingapp.databinding.MyRecipesBinding
import com.example.cookingapp.models.RecipeModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {

    private lateinit var homePagebinding: HomePageBinding
    private lateinit var activitymainbinding: ActivityMainBinding
    private lateinit var myRecipesBinding: MyRecipesBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        homePagebinding = HomePageBinding.inflate(layoutInflater)
        activitymainbinding = ActivityMainBinding.inflate(layoutInflater)
        myRecipesBinding = MyRecipesBinding.inflate(layoutInflater)

        setContentView(homePagebinding.root)

        activitymainbinding.start.setOnClickListener {
            setContentView(homePagebinding.root)
        }

        bottomBarNavigation()

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController



    }


    private fun bottomBarNavigation(){
        homePagebinding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inspirations -> {
                    replaceFragment(InspirationFragment())
                    true
                }
                R.id.myrecipes -> {
                    replaceFragment(RecipesFragment())
                    true
                }
                R.id.cooknow -> {
                    true
                }
                R.id.settings -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(homePagebinding.fragmentContainerView.id,fragment)
        fragmentTransaction.commit()
    }
}