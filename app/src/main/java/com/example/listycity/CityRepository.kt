package com.example.listycity

import androidx.compose.runtime.mutableStateListOf

class CityRepository {
    // keep mutable app data private so other classes cannot change it directly
    private val _cities = mutableStateListOf(
        "Edmonton", "Vancouver", "Moscow",
        "Sydney", "Berlin", "Vienna",
        "Tokyo", "Beijing", "Osaka",
        "New Delhi"
    )

    // Read only list for UI to display
    val cities: List<String>
        get() = _cities

    fun addCity(city: String) {
        _cities.add(city)
    }
}