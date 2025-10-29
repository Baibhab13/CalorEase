package com.example.calorie_counter.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NutritionixApi {
    @Headers(
        "Your ID",
        "x-app-key: Your API KEY",
        "Content-type: application/json"
    )
    @POST("v2/natural/nutrients")
    fun getNutrients(@Body request: FoodRequest): Call<FoodResponse>
}