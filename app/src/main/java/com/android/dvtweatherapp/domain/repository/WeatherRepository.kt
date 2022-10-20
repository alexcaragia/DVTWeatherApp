package com.android.dvtweatherapp.domain.repository

import com.android.dvtweatherapp.data.remote.WeatherDto
import com.android.dvtweatherapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeatherData(
        latitude: Double, longitude: Double, appId: String, units: String
    ): Flow<Response<WeatherDto>>

    suspend fun getForecastWeatherData(
        latitude: Double, longitude: Double, appId: String, units: String
    ): Flow<Response<WeatherDto>>
}
