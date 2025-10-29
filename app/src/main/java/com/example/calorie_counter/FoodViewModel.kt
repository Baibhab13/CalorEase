package com.example.calorie_counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calorie_counter.api.FoodItem
import com.example.calorie_counter.api.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val repository: FoodRepository = FoodRepository()
) : ViewModel() {

    private val _foods = MutableStateFlow<List<FoodItem>>(emptyList())
    val foods: StateFlow<List<FoodItem>> = _foods

    private val _totalCalories = MutableStateFlow(0)
    val totalCalories: StateFlow<Int> = _totalCalories

    private val _totalProtein = MutableStateFlow(0)
    val totalProtein: StateFlow<Int> = _totalProtein

    fun addFood(query: String) {
        viewModelScope.launch {
            repository.getFood(query) { food ->
                food?.let {
                    _foods.value = _foods.value + it
                    recalculateTotals()
                }
            }
        }
    }

    private fun recalculateTotals() {
        _totalCalories.value = _foods.value.sumOf { it.nf_calories.toInt() }
        _totalProtein.value = _foods.value.sumOf { it.nf_protein.toInt() }
    }
}
