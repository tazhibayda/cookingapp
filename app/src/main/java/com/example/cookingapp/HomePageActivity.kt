package com.example.cookingapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.example.cookingapp.databinding.ActivityMainBinding
import com.example.cookingapp.databinding.HomePageBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {

    private lateinit var homePagebinding: HomePageBinding
    private lateinit var activitymainbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        homePagebinding = HomePageBinding.inflate(layoutInflater)
        activitymainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activitymainbinding.root)
        activitymainbinding.start.setOnClickListener {
            setContentView(homePagebinding.root)
        }
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.item1 -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.item2 -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }


    }
}