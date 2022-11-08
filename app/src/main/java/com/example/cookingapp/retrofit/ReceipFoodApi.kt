package com.example.cookingapp.retrofit

import com.example.cookingapp.pojo.ReceipFoodList
import retrofit2.Call
import retrofit2.http.GET

interface ReceipFoodApi {

    @GET("random.php")
    fun getRandomReceip(): Call<ReceipFoodList>


}