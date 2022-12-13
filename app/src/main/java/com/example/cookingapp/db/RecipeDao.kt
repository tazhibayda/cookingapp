package com.example.cookingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookingapp.pojo.Meal

@Dao
interface RecipeDao {
   /* @Insert
    suspend fun insertMeal(meal: Meal)

    @Update
    suspend fun updateMeal(meal: Meal)
    */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(meal: Meal)

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM recipeinformation")
    fun getAllMeals():LiveData<List<Meal>>

    @Query("Select * FROM recipeinformation where idMeal=:id")
    fun getById(id:Int):Meal

}