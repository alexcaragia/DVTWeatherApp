package com.android.dvtweatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    @SerializedName("main")
    val temperatureData: TemperatureData,
    @SerializedName("weather")
    val weatherInformationList: List<WeatherInfo> = emptyList()
)
