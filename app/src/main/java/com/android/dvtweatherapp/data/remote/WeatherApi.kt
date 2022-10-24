package com.android.dvtweatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

private const val WEATHER_MEASUREMENT_UNITS = "metric"

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String,
        @Query("units") units: String = WEATHER_MEASUREMENT_UNITS
    ): WeatherDto

    @GET("forecast")
    suspend fun getForecastWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String,
        @Query("units") units: String = WEATHER_MEASUREMENT_UNITS
    ): ForecastDto
}
