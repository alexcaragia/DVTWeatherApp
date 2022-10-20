package com.android.dvtweatherapp.domain.repository

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double)
}
