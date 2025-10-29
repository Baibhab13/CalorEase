package com.example.calorie_counter.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NutritionixApi {
    @Headers(
        "x-app-id: bfd30ef3",
        "x-app-key: 5818f8bcd8660ea75abeec92d026427e",
        "Content-type: application/json"
    )
    @POST("v2/natural/nutrients")
    fun getNutrients(@Body request: FoodRequest): Call<FoodResponse>
}