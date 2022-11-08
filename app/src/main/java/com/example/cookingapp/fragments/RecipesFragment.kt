package com.example.cookingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cookingapp.HomePageActivity
import com.example.cookingapp.R
import com.example.cookingapp.adapters.RecipeAdapter
import com.example.cookingapp.models.RecipeModel


class RecipesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        val recieps: RecyclerView = view.findViewById(R.id.my_recipes)

        val reciepList: ArrayList<RecipeModel> = ArrayList<RecipeModel>()
        reciepList.add(RecipeModel("Veggie breakfast", "Healthy food for busy people", 5, 1,
            R.drawable.day2_image))
        reciepList.add(RecipeModel("Italian pasta", "Tasty traditional dish.\nNot only for Italian who went to Malta", 45, 4,
            R.drawable.italian_pasta))
        reciepList.add(RecipeModel("Cheese’n’Meat", "Let’s pray only for \nCheesus!", 3, 2,
            R.drawable.cheesen_meat))
        reciepList.add(RecipeModel("Pumpkin Soup", "Feel the spiciness and  season vegetables", 50, 4,
            R.drawable.pupmpkin_soup))
        reciepList.add(RecipeModel("Asian inspired pasta", "Missing pad-thai and living in Italy? Check this out", 45, 4,
            R.drawable.asian_pasta))
        reciepList.add(RecipeModel("Croatian wedding", "Some tasty things", 90, 4,
            R.drawable.croatia))


        val recipeAdapter = RecipeAdapter(HomePageActivity(), reciepList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recieps.layoutManager = layoutManager
        recieps.adapter = recipeAdapter


        return view
    }


}