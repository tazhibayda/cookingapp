package com.example.cookingapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cookingapp.ReceipActivity
import com.example.cookingapp.databinding.FragmentInspirationsBinding
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.viewModel.InspirationViewModel


class InspirationFragment : Fragment() {
    private lateinit var binding :FragmentInspirationsBinding
    private lateinit var homeMvvm : InspirationViewModel
    private lateinit var randomMeal: Meal

    companion object{
        const val RECEIP_ID = "com.example.cookingapp.fragments.idReceip"
        const val RECEIP_NAME = "com.example.cookingapp.fragments.nameReceip"
        const val RECEIP_THUMB  = "com.example.cookingapp.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this)[InspirationViewModel::class.java]

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentInspirationsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeMvvm.getRandomMeal()
        observeRandomMeal()
        onRandomClick()
    }

    private fun onRandomClick() {
        binding.randomCardReceips.setOnClickListener {
            val intent = Intent(activity, ReceipActivity::class.java)
            intent.putExtra(RECEIP_ID,randomMeal.idMeal)
            intent.putExtra(RECEIP_NAME,randomMeal.strMeal)
            intent.putExtra(RECEIP_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner,
            {meal->
            Glide.with(this@InspirationFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomReceip)
            this.randomMeal =meal
        })
    }
}


