package com.example.listycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity.ui.theme.ListyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cityRepository = CityRepository()

        setContent {
            ListyCityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CityListScreen(
                        cities = cityRepository.cities,
                        onAddCity = { cityRepository.addCity(it)},
                        onDeleteCity = { cityRepository.deleteCity(it)},
                        modifier = Modifier.padding( paddingValues = innerPadding)
                    )
                }
            }
        }
    }
}

// @Composable means this function describes part of the app's UI
@Composable
fun CityListScreen(
    //cities: List<String> is te list of the city names that
    //this screen receives from MainActivity
    cities: List<String>,
    //means that CityListScreen receives a function as a parameter
    //which will take on String, which will be city typed by user
    //then returns unit - performs action but not return value
    onAddCity: (String) -> Unit,
    //modifier: Modifier = Modifier allows layout information,
    //such as padding, to be passed into this screen
    onDeleteCity: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var newCityName by remember { mutableStateOf(value = "") }
    //holds value or announces changes (hold selected city or no city)
    var selectedCity by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(all = 16.dp)) {
            OutlinedTextField(
                value = newCityName,
                onValueChange = { newCityName = it },
                label = { Text("City name") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newCityName.isNotBlank()) {
                        onAddCity(newCityName)
                        newCityName = ""
                    }
                }
            ) {
                Text("Add City")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (selectedCity != null) {
                        //!! not null
                        onDeleteCity(selectedCity!!)
                        selectedCity = null
                    }
                }
            ) {
                Text("Delete City")
            }
        }

        // LazyColumn is the Compose for a basic scrolling ListView
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            // items(cities) loops through the city list and
            // creates one UI row for each city.
            items(cities) { city ->
                CityRow(
                    city = city,
                    isSelected = (city == selectedCity),
                    onClick = { selectedCity = city}
                )
            }
        }
    }
}

@Composable
fun CityRow(city: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = city,
        fontSize = 28.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(if (isSelected) Color.LightGray else Color.Transparent)
            .padding(horizontal = 18.dp, vertical = 14.dp)
    )
}