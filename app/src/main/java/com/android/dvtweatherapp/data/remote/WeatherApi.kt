package com.android.dvtweatherapp.data.remote

interface WeatherApi {
    suspend fun getWeatherData()
}
