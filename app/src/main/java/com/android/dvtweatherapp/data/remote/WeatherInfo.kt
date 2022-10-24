package com.android.dvtweatherapp.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    @SerializedName("main")
    val name: String,
    val description: String
)
