package com.example.cookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookingapp.adapters.RecipeAdapter
import com.example.cookingapp.databinding.ActivityMainBinding
import com.example.cookingapp.databinding.HomePageBinding
import com.example.cookingapp.databinding.MyRecipesBinding
import com.example.cookingapp.models.RecipeModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {

    private lateinit var homePagebinding: HomePageBinding
    private lateinit var activitymainbinding: ActivityMainBinding
    private lateinit var myRecipesBinding: MyRecipesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        homePagebinding = HomePageBinding.inflate(layoutInflater)
        activitymainbinding = ActivityMainBinding.inflate(layoutInflater)
        myRecipesBinding = MyRecipesBinding.inflate(layoutInflater)
        setContentView(activitymainbinding.root)
        activitymainbinding.start.setOnClickListener {
            setContentView(myRecipesBinding.root)
        }
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inspirations -> {
                    // Respond to navigation item 1 click
                    Toast.makeText(this, "123",Toast.LENGTH_LONG)

                    true
                }
                R.id.myrecipes -> {
                    Toast.makeText(this, "ASDASD",Toast.LENGTH_LONG)
//                    setContentView(myRecipesBinding.root)
                    // Respond to navigation item 2 click
                    true
                }
                R.id.cooknow -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.settings -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }




        val recieps = myRecipesBinding.myRecipes
        val reciepList: ArrayList<RecipeModel> = ArrayList<RecipeModel>()
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))

        val recipeAdapter = RecipeAdapter(this, reciepList)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recieps.layoutManager = linearLayoutManager
        recieps.adapter = recipeAdapter
    }
}