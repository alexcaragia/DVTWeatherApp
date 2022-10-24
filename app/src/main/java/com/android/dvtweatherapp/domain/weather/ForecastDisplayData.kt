package com.android.dvtweatherapp.domain.weather

import com.android.dvtweatherapp.data.remote.TemperatureData

data class ForecastDisplayData(
    val weekDay: String,
    val weatherType: WeatherType,
    val temperatureData: TemperatureData
)
