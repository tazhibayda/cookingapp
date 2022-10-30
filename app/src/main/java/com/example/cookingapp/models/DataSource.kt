package com.example.cookingapp.models

import com.example.cookingapp.R

class DataSource {

    fun loadData():List<InspirationsModel>{
        return listOf<InspirationsModel>(
            InspirationsModel(R.id.RecipesTitle.toString(), R.id.RecipesName.toString(),
                R.id.recipe_time,R.id.recipe_ppl,R.id.dayImage)
        )
    }
}