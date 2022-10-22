package com.example.cookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
            setContentView(homePagebinding.root)
        }


        homePagebinding.bottomNavigatinView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inspirations -> {
                    true
                }
                R.id.myrecipes -> {
                    setContentView(myRecipesBinding.root)
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

        myRecipesBinding.bottomNavigatinView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inspirations -> {
                    setContentView(homePagebinding.root)
                    true
                }
                R.id.myrecipes -> {
                    setContentView(myRecipesBinding.root)
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



        val recieps = myRecipesBinding.myRecipes
        val reciepList: ArrayList<RecipeModel> = ArrayList<RecipeModel>()
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("What is Lorem Ipsum?",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java ajkn", "aasdassdhiasdasdbashd",90,6, R.drawable.dishes))

        val recipeAdapter = RecipeAdapter(this, reciepList)

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recieps.layoutManager = layoutManager
        recieps.adapter = recipeAdapter
    }
}