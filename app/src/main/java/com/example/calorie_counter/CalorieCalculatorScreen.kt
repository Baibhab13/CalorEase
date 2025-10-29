package com.example.calorie_counter

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calorie_counter.api.FoodItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalorieCalculatorApp(viewModel: FoodViewModel) {
    var query by remember { mutableStateOf("") }
    val foodList by viewModel.foods.collectAsState()
    val totalCalories by viewModel.totalCalories.collectAsState()
    val totalProtein by viewModel.totalProtein.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calorie Tracker") },
//                action = {
//                    RadioButton(
//                        selected = viewModel.isMetric.value,
//                        onClick = { viewModel.isMetric.value = true }
//                    )
//                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                maxLines = 1,
                label = { Text("What did you eat? (e.g. 2 eggs, 100g paneer)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.addFood(query)
                    query = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Food")
            }

            HorizontalDivider()


            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    "Total Calories: $totalCalories kcal",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text("Total Protein: $totalProtein g", style = MaterialTheme.typography.bodyLarge)
            }

            HorizontalDivider()

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(foodList) { food ->
                    FoodCard(food)
                }
            }
        }
    }
}


@Composable
fun FoodCard(food: FoodItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(food.food_name, style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer)
            Text(
                "${food.nf_calories.toInt()} kcal, ${food.nf_protein.toInt()} g protein",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES) // Added showBackground for better visibility
@Composable
fun FoodCardPreview() {
    FoodCard(food = FoodItem("Sample Food", 100f, 20f, 30f, 40f))
}

@Preview(showBackground = true) // Added showBackground for better visibility
@Composable
fun CalorieCalculatorAppPreview() {
    CalorieCalculatorApp(viewModel = FoodViewModel())
}