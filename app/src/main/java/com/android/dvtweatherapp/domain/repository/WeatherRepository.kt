package com.android.dvtweatherapp.domain.repository

import com.android.dvtweatherapp.domain.util.Response
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherDisplayData

interface WeatherRepository {
    suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Response<WeatherDisplayData>

    suspend fun getForecastWeatherData(
        latitude: Double,
        longitude: Double
    ): Response<List<ForecastDisplayData>?>
}
