package com.example.cookingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingapp.db.RecipeDatabase
import com.example.cookingapp.pojo.*
import com.example.cookingapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InspirationViewModel(
    private val recipeDatabase:RecipeDatabase
    ) : ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategoryList>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    private var favouritesRecipesLiveData = recipeDatabase.recipDao().getAllMeals()
    private val searchedReceipsLiveData =MutableLiveData<List<Meal>>()

    private var savedStateRandomMeal :Meal? = null

    init {
        getRandomMeal()
    }
    private fun getRandomMeal() {
        RetrofitInstance.api.getRandomReceip().enqueue(object : Callback<ReceipFoodList> {
            override fun onResponse(
                call: Call<ReceipFoodList>,
                response: Response<ReceipFoodList>,
            ) {
                if (response.body() != null) {
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                    savedStateRandomMeal = randomMeal
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<ReceipFoodList>, t: Throwable) {
                Log.d("InspirationFragment", t.message.toString())
            }
        })

    }

    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object :Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { categoryList ->
                    categoriesLiveData.postValue(categoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("InspirationViewModel", t.message.toString())
            }

        })
    }
    fun deleteRecipe(meal: Meal){
        viewModelScope.launch {
            recipeDatabase.recipDao().delete(meal)
        }
    }
    fun insertRecipe(meal: Meal){
        viewModelScope.launch {
            recipeDatabase.recipDao().update(meal)
        }
    }
    fun searchMeals( searchQuery :String) = RetrofitInstance.api.searchMeals(searchQuery).enqueue(
        object :Callback<ReceipFoodList>{
            override fun onResponse(
                call: Call<ReceipFoodList>,
                response: Response<ReceipFoodList>,
            ) {
                val mealslList = response.body()?.meals
                mealslList?.let {
                    searchedReceipsLiveData.postValue(it)
                }
            }

            override fun onFailure(call: Call<ReceipFoodList>, t: Throwable) {
                Log.e("InspirationViewModel",t.message.toString())
            }
        }
    )


    fun observeSearchMealsLiveData():LiveData<List<Meal>> = searchedReceipsLiveData

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observePopularItemsLiveData(): LiveData<List<MealsByCategoryList>> {
        return popularItemsLiveData
    }
    fun observeCategoriesLiveData():LiveData<List<Category>>{
        return categoriesLiveData
    }
    fun observeFavouritesRecipesLiveDara():LiveData<List<Meal>>{
        return favouritesRecipesLiveData
    }

}