package com.android.dvtweatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("list")
    val forecastInfoList: List<WeatherDto>
)
