package com.example.cookingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cookingapp.databinding.FragmentInspirationsBinding
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.viewModel.InspirationViewModel


class InspirationFragment : Fragment() {
    private lateinit var binding :FragmentInspirationsBinding
    private lateinit var homeMvvm : InspirationViewModel



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
    }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner, object:Observer<Meal>{
            override fun onChanged(t: Meal?) {
            Glide.with(this@InspirationFragment)
                .load(t!!.strMealThumb)
                .into(binding.imgRandomReceip)
            }
        })
    }


}


