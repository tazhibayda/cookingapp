package com.example.cookingapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cookingapp.CategoryMealsActivity
import com.example.cookingapp.HomePageActivity
import com.example.cookingapp.ReceipActivity
import com.example.cookingapp.adapters.CategoriesAdapter
import com.example.cookingapp.adapters.PopularAdapter
import com.example.cookingapp.databinding.FragmentInspirationsBinding
import com.example.cookingapp.pojo.MealsByCategoryList
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.viewModel.InspirationViewModel


class InspirationFragment : Fragment() {
    private lateinit var binding: FragmentInspirationsBinding
    private lateinit var viewModel: InspirationViewModel
    private lateinit var randomMeal: Meal
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var categoriesAdapter :CategoriesAdapter

    companion object {
        const val RECEIP_ID = "com.example.cookingapp.fragments.idReceip"
        const val RECEIP_NAME = "com.example.cookingapp.fragments.nameReceip"
        const val RECEIP_THUMB = "com.example.cookingapp.fragments.thumbMeal"
        const val CATEGORY_NAME  = "com.example.cookingapp.fragments.categoryName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as HomePageActivity).viewModel
        popularAdapter = PopularAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInspirationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItemsRecyclerView()
        initObservers()
        initListeners()

        viewModel.onViewCreated()

        prepareCategoriesRecyclerView()
        viewModel.getCategories()
        observeCategoriesLiveData()


        onCategoryClick()
    }

    private fun onCategoryClick() {
        categoriesAdapter.OnItemclick = {category ->
            val intent = Intent(activity,CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME,category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareCategoriesRecyclerView() {
        categoriesAdapter = CategoriesAdapter()
        binding.recyclerViewCategories.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL, false)
            adapter = categoriesAdapter

        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer{categories->
               categoriesAdapter.setCategoryList(categories)
        })
    }

    private fun preparePopularItemsRecyclerView() {
        popularAdapter = PopularAdapter()
        binding.recyclerViewReceipsPopular.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
    }

    private fun initListeners() {
        binding.randomCardReceips.setOnClickListener {
            val intent = Intent(activity, ReceipActivity::class.java)
            intent.putExtra(RECEIP_ID, randomMeal.idMeal)
            intent.putExtra(RECEIP_NAME, randomMeal.strMeal)
            intent.putExtra(RECEIP_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun initObservers() {
        viewModel.observeRandomMealLiveData().observe(
            viewLifecycleOwner
        ) { meal ->
            Glide.with(this@InspirationFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomReceip)
            this.randomMeal = meal
        }






    }

        }



