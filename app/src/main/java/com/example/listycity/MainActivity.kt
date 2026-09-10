package com.example.listycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    //modifier: Modifier = Modifier allows layout information,
    //such as padding, to be passed into this screen
    modifier: Modifier = Modifier
) {
    // LazyColumn is the Compose for a basic scrolling ListView
    LazyColumn(modifier = modifier.fillMaxSize()) {
        // items(cities) loops through the city list and
        // creates one UI row for each city.
        items(cities) {city ->
            CityRow(city = city)
        }
    }
}