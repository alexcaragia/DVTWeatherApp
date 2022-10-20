package com.android.dvtweatherapp.data.repository

import com.android.dvtweatherapp.domain.repository.WeatherRepository

class DefaultWeatherRepository : WeatherRepository {
    override suspend fun getWeatherData(latitude: Double, longitude: Double) {
    }
}
