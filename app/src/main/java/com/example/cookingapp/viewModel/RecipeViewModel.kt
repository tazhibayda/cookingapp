package com.example.cookingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingapp.db.RecipeDatabase
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.pojo.ReceipFoodList
import com.example.cookingapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel(
    val recipeDatabase: RecipeDatabase
):ViewModel() {

    private var receipDetailsLiveData = MutableLiveData<Meal>()

    fun getReceipDetail(id:String){
        RetrofitInstance.api.getReceipDetails(id).enqueue(object :Callback<ReceipFoodList>{
            override fun onResponse(
                call: Call<ReceipFoodList>,
                response: Response<ReceipFoodList>,
            ) {
                if(response.body() !=null){
                    receipDetailsLiveData.value = response.body()!!.meals[0]
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<ReceipFoodList>, t: Throwable) {
            Log.d("ReceipActivity", t.message.toString())
            }

        })
    }
    fun observerReceipDetailsLiveData():LiveData<Meal>{
        return receipDetailsLiveData
    }
    fun insertRecipe(meal: Meal){
        viewModelScope.launch {
            recipeDatabase.recipDao().update(meal)
        }
    }

}