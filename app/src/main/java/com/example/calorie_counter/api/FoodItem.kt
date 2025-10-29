package com.example.calorie_counter.api

data class FoodItem (
    val food_name: String,
    val nf_calories: Float,
    val nf_protein: Float,
    val nf_total_carbohydrate: Float,
    val nf_total_fat: Float
)

//Request data from the website
data class FoodRequest(val query: String)

//GET data from the website
data class FoodResponse (val foods: List<FoodItem>)