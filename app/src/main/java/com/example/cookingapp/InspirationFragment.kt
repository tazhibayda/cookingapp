package com.example.cookingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cookingapp.databinding.HomePageBinding

class InspirationFragment: Fragment() {

    private lateinit var homePagebinding: HomePageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_inspirations, container, false)

        homePagebinding = HomePageBinding.inflate(layoutInflater)
        homePagebinding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.myrecipes -> {
                    Toast.makeText(HomePageActivity(),"asdsad",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_inspirationFragment_to_recipesFragment)
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

        return view
    }


}