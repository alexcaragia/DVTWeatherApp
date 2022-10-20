package com.android.dvtweatherapp.data.remote

import com.android.dvtweatherapp.domain.util.Response
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Flow<Response<WeatherDto>>

    @GET("forecast")
    suspend fun getForecastWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Flow<Response<WeatherDto>>
}
