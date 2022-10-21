package com.android.dvtweatherapp.domain.weather

data class WeatherDisplayData(
    val currentTemperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val weatherType: WeatherType? = null
)
