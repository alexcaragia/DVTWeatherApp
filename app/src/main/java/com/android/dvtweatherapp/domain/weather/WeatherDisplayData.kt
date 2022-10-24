package com.android.dvtweatherapp.domain.weather

data class WeatherDisplayData(
    val currentTemperature: Float,
    val maxTemperature: Float,
    val minTemperature: Float,
    val weatherType: WeatherType? = null
)
