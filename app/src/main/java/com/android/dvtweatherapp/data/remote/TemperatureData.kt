package com.android.dvtweatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class TemperatureData(
    @SerializedName("temp")
    val currentTemperature: Double,
    @SerializedName("temp_min")
    val minTemperature: Double,
    @SerializedName("temp_max")
    val maxTemperature: Double
)
