package com.example.cookingapp.retrofit

import com.example.cookingapp.pojo.CategoryList
import com.example.cookingapp.pojo.MealsByCategoryList
import com.example.cookingapp.pojo.ReceipFoodList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReceipFoodApi {

    @GET("random.php")
    fun getRandomReceip(): Call<ReceipFoodList>

    @GET("lookup.php?")
    fun getReceipDetails(@Query("i")id:String):Call<ReceipFoodList>

    @GET("filter.php?")
    fun getPopulatItems(@Query("c")categoryname:String) :Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories():Call<CategoryList>

}