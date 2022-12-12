package com.example.cookingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cookingapp.databinding.ActivityReceipBinding
import com.example.cookingapp.db.RecipeDatabase
import com.example.cookingapp.fragments.InspirationFragment
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.viewModel.RecipeViewModel
import com.example.cookingapp.viewModel.RecipeViewModelFactory

class ReceipActivity : AppCompatActivity() {
    private lateinit var receipId :String
    private lateinit var receipName :String
    private lateinit var receipThumb :String
    private lateinit var binding :ActivityReceipBinding
    private lateinit var receipMvvm :RecipeViewModel
    private lateinit var youtubeLink :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeDatabase = RecipeDatabase.getInstance(this)
        val viewModelFactory = RecipeViewModelFactory(recipeDatabase)
        receipMvvm = ViewModelProvider(this,viewModelFactory).get(RecipeViewModel::class.java)
/*
        receipMvvm =ViewModelProvider(this)[RecipeViewModel::class.java]
*/

        getRecipeInformationFromIntent()
        setInformationInViews()
        loadingCase()
        onResponseCase()
        onYoutube()
        receipMvvm.getReceipDetail(receipId)
        observeRecipeLiveData()
        onFavouriteClick()
    }

    private fun onFavouriteClick() {
        binding.btnAddToFav.setOnClickListener{
            recipeToSave?.let {
                receipMvvm.insertRecipe(it)
                Toast.makeText(this,"Recipe saved",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onYoutube() {
        binding.imageYoutube.setOnClickListener{
            val intent  = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }
    private var recipeToSave:Meal? =null
    private fun observeRecipeLiveData() {
        receipMvvm.observerReceipDetailsLiveData().observe(this,object : Observer<Meal>{
            @SuppressLint("SetTextI18n")
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val recipe = t
                recipeToSave = recipe

                binding.categotyText.text = recipe!!.strCategory
                binding.areaText.text = recipe.strArea
                binding.instructionsText.text = recipe.strInstructions
                youtubeLink = recipe.strYoutube
            }

        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(receipThumb)
            .into(binding.imgReceipDetail)
        binding.collapsingToolbar.title = receipName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun getRecipeInformationFromIntent() {
        val intent =intent
        receipId  = intent.getStringExtra(InspirationFragment.RECEIP_ID)!!
        receipName = intent.getStringExtra(InspirationFragment.RECEIP_NAME)!!
        receipThumb = intent.getStringExtra(InspirationFragment.RECEIP_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFav.visibility = View.INVISIBLE
        binding.instructionsText.visibility = View.INVISIBLE
        binding.areaText.visibility = View.INVISIBLE
        binding.categotyText.visibility = View.INVISIBLE
        binding.imageYoutube.visibility = View.INVISIBLE
    }
    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFav.visibility = View.VISIBLE
        binding.instructionsText.visibility = View.VISIBLE
        binding.areaText.visibility = View.VISIBLE
        binding.categotyText.visibility = View.VISIBLE
        binding.imageYoutube.visibility = View.VISIBLE
    }
}