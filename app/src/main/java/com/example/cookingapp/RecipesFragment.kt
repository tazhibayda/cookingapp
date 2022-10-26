package com.example.cookingapp

import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cookingapp.adapters.RecipeAdapter
import com.example.cookingapp.databinding.HomePageBinding
import com.example.cookingapp.models.RecipeModel


class RecipesFragment : Fragment() {

    private lateinit var homePagebinding: HomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_recipes, container, false)

        val recieps:RecyclerView = view.findViewById(R.id.my_recipes)

        val reciepList: ArrayList<RecipeModel> = ArrayList<RecipeModel>()
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("What is Lorem Ipsum?",
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java", "asdhiasdasdbashd",45,3, R.drawable.chef))
        reciepList.add(RecipeModel("DSA in Java ajkn", "aasdassdhiasdasdbashd",90,6, R.drawable.dishes))

        val recipeAdapter = RecipeAdapter(HomePageActivity(), reciepList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recieps.layoutManager = layoutManager
        recieps.adapter = recipeAdapter


        homePagebinding = HomePageBinding.inflate(layoutInflater)
        homePagebinding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inspirations -> {
                    findNavController().navigate(R.id.action_recipesFragment_to_inspirationFragment)
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