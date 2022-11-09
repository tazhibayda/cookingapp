package com.example.cookingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.pojo.ReceipFoodList
import com.example.cookingapp.retrofit.retrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class InspirationViewModel:ViewModel() {
    private var randomMealLiveData  = MutableLiveData<Meal>()
    fun getRandomMeal(){
        retrofitInstance.api.getRandomReceip().enqueue(object : Callback<ReceipFoodList>{
            override fun onResponse(
                call: Call<ReceipFoodList>,
                response: Response<ReceipFoodList>
            ) {
                if(response.body() != null){
                    val randomMeal :Meal  = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<ReceipFoodList>, t: Throwable) {
                Log.d("InspirationFragment", t.message.toString())
            }
        })

    }

    fun observeRandomMealLiveData():LiveData<Meal>{
        return randomMealLiveData
    }
}