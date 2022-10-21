package com.android.dvtweatherapp.domain.repository

import com.android.dvtweatherapp.data.remote.WeatherDto
import com.android.dvtweatherapp.domain.util.Response

interface WeatherRepository {
    suspend fun getCurrentWeatherData(latitude: Double, longitude: Double): Response<WeatherDto>
    suspend fun getForecastWeatherData(latitude: Double, longitude: Double): Response<WeatherDto>
}
