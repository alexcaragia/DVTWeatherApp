package com.android.dvtweatherapp.domain.weather

data class ForecastDisplayData(
    val dayOfWeek: String,
    val maxTemperature: String,
    val weatherType: ForecastWeatherType
)
