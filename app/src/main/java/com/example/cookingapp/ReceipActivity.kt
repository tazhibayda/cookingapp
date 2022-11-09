package com.example.cookingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cookingapp.databinding.ActivityReceipBinding
import com.example.cookingapp.fragments.InspirationFragment
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.viewModel.ReceipActivityViewModel

class ReceipActivity : AppCompatActivity() {
    private lateinit var receipId :String
    private lateinit var receipName :String
    private lateinit var receipThumb :String
    private lateinit var binding :ActivityReceipBinding
    private lateinit var receipMvvm :ReceipActivityViewModel
    private lateinit var youtubeLink :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receipMvvm =ViewModelProvider(this)[ReceipActivityViewModel::class.java]


        getReceipInformationFromIntent()
        setInformationInViews()
        loadingCase()
        onResponseCase()
        onyoutube()
        receipMvvm.getReceipDetail(receipId)
        observeReceipLiveData()
    }

    private fun onyoutube() {
        binding.imageYoutube.setOnClickListener{
            val intent  = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }
    }

    private fun observeReceipLiveData() {
        receipMvvm.observerReceipDetailsLiveData().observe(this,object : Observer<Meal>{
            @SuppressLint("SetTextI18n")
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val receip = t
                binding.categotyText.text = receip!!.strCategory
                binding.areaText.text = receip.strArea
                binding.instructionsText.text = receip.strInstructions

                youtubeLink = receip.strYoutube
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

    private fun getReceipInformationFromIntent() {
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