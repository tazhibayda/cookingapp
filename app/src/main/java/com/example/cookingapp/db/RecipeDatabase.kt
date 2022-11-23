package com.example.cookingapp.db

import android.content.Context
import androidx.room.*
import com.example.cookingapp.pojo.Meal


@Database(entities = arrayOf(Meal::class), version = 1)
@TypeConverters(RecipeTypeConverter ::class)
abstract class RecipeDatabase :RoomDatabase() {
    abstract fun recipDao():RecipeDao

    companion object {
        @Volatile
        var INSTANCE: RecipeDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RecipeDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as RecipeDatabase
        }
    }
}