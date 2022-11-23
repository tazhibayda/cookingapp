package com.example.cookingapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class RecipeTypeConverter {

    @TypeConverter
    fun AnyToString(attribute : Any?) :String{
        if(attribute ==null)
            return ""
        return attribute.toString()
    }
    @TypeConverter
    fun StringToAny(attribute: String?) :Any{
        if(attribute ==null)
            return ""
        return attribute
    }
}