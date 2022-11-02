package com.example.cookingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingapp.adapters.InspirationsAdapter
import com.example.cookingapp.databinding.FragmentHomeBinding
import com.example.cookingapp.databinding.FragmentInspirationsBinding
import com.example.cookingapp.models.InspirationsModel

class InspirationFragment : Fragment() {
    private  var _binding :FragmentInspirationsBinding? = null
    private  val binding: FragmentInspirationsBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val myview = inflater.inflate(R.layout.fragment_inspirations, container, false)

        val recipesDay : RecyclerView = myview.findViewById(R.id.RecipesDayRv)

        val recipesDayList :ArrayList<InspirationsModel> = ArrayList()
        recipesDayList.add(InspirationsModel("Recipes \nof the day", "Roasted Pumpkin Soup",
            50, 4, R.drawable.day_image))
        recipesDayList.add(InspirationsModel("Cook \nlike pro","Veggie breakfast",
            5,1,R.drawable.day2_image))
        recipesDayList.add(InspirationsModel("Check \nnew updates", "Cheese’n’Meat",
            3,2,R.drawable.day3_image))

/*
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
*/
        val insAdapter = InspirationsAdapter(HomePageActivity(),recipesDayList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recipesDay.layoutManager = layoutManager
        recipesDay.adapter = insAdapter
        return myview
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}