package com.example.cookingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookingapp.pojo.Category
import com.example.cookingapp.pojo.MealByCategory
import com.example.cookingapp.pojo.MealsByCategoryList
import com.example.cookingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel :ViewModel() {
    val mealsLiveData = MutableLiveData<List<MealByCategory>>()
    fun getMealsByCategory(categoryName : String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>,
            ) {
                response.body()?.let{mealsList ->
                    mealsLiveData.postValue(mealsList.meals)
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
            Log.d("CategoryMealsViewModel", t.message.toString() )
            }

        })
    }
    fun observeMealsLiveData():LiveData<List<MealByCategory>>{
        return mealsLiveData
    }
}