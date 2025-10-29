package com.example.calorie_counter.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodRepository {
    fun getFood(query: String, onResult: (FoodItem?) -> Unit) {
        val call = ApiClient.instance.getNutrients(FoodRequest(query))
        call.enqueue(object : retrofit2.Callback<FoodResponse> {
            override fun onResponse(
                call: Call<FoodResponse>,
                response: retrofit2.Response<FoodResponse>
            ) {
                if (response.isSuccessful) {
                    onResult(response.body()?.foods?.firstOrNull())
                } else {
                    onResult(null)
                }
            }
            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }
}
