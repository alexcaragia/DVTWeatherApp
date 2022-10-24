package com.android.dvtweatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("dt")
    val timestamp: Long,
    @SerializedName("main")
    val temperatureData: TemperatureData,
    @SerializedName("weather")
    val weatherInformationList: List<WeatherInfo> = emptyList()
)
