package com.example.cookingapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingapp.db.RecipeDatabase

class InspirationViewModelFactory(
    private val recipeDatabase: RecipeDatabase
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InspirationViewModel(recipeDatabase) as T
    }
}