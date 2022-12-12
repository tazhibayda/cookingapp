package com.example.cookingapp.models

import androidx.room.Entity


@Entity
data class Dish(
    val name: String,
    val steps: List<CookStepDataModel>,
)

data class CookStepDataModel(
    val description:String,
)

data class CookStep(
    val stepNumber: Int,
    val description:String,
    val numberOfSteps:Int,
)