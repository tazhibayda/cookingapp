package com.example.cookingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookingapp.pojo.Meal
import com.example.cookingapp.pojo.ReceipFoodList
import com.example.cookingapp.retrofit.retrofitInstance
import com.example.cookingapp.retrofit.retrofitInstance.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ReceipActivityViewModel:ViewModel() {

    private var receipDetailsLiveData = MutableLiveData<Meal>()

    fun getReceipDetail(id:String){
        retrofitInstance.api.getReceipDetails(id).enqueue(object :Callback<ReceipFoodList>{
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
}