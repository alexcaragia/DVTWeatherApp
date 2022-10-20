package com.android.dvtweatherapp.data.repository

import com.android.dvtweatherapp.data.remote.WeatherApi
import com.android.dvtweatherapp.data.remote.WeatherDto
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import com.android.dvtweatherapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class DefaultWeatherRepository(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    override suspend fun getCurrentWeatherData(
        latitude: Double, longitude: Double, appId: String, units: String
    ): Flow<Response<WeatherDto>> {

    }

    override suspend fun getForecastWeatherData(
        latitude: Double, longitude: Double, appId: String, units: String
    ): Flow<Response<WeatherDto>> {

    }
}
