package com.android.dvtweatherapp.data.repository

import com.android.dvtweatherapp.BuildConfig
import com.android.dvtweatherapp.common.ErrorMessages
import com.android.dvtweatherapp.data.mappers.toForecastDisplayDataList
import com.android.dvtweatherapp.data.mappers.toWeatherDisplayData
import com.android.dvtweatherapp.data.remote.WeatherApi
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import com.android.dvtweatherapp.domain.util.Response
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherDisplayData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultWeatherRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherApi: WeatherApi
) : WeatherRepository {
    private val appId = BuildConfig.WEATHER_API_KEY

    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Response<WeatherDisplayData> {
        return withContext(ioDispatcher) {
            try {
                val result = weatherApi.getCurrentWeatherData(latitude, longitude, appId)
                Response.Success(data = result.toWeatherDisplayData())
            } catch (exception: Exception) {
                Response.Error(message = exception.message ?: ErrorMessages.UNKNOWN_ERROR.message)
            }
        }
    }

    override suspend fun getForecastWeatherData(
        latitude: Double,
        longitude: Double
    ): Response<List<ForecastDisplayData>?> {
        return withContext(ioDispatcher) {
            Response.Success(
                data = weatherApi.getForecastWeatherData(latitude, longitude, appId)
                    .toForecastDisplayDataList()
            )
        }
    }
}
