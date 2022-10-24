package com.android.dvtweatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class TemperatureData(
    @SerializedName("temp")
    val currentTemperature: Float,
    @SerializedName("temp_min")
    val minTemperature: Float,
    @SerializedName("temp_max")
    val maxTemperature: Float
)
