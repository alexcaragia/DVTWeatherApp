package com.android.dvtweatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class ForecastInfo(
    @SerializedName("dt")
    val timestamp: Long,
    @SerializedName("main")
    val temperatureData: TemperatureData
)
