package com.example.cookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cookingapp.databinding.HomePageBinding

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: HomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}