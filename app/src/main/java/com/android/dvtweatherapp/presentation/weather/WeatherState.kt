package com.android.dvtweatherapp.presentation.weather

import androidx.annotation.StringRes
import com.android.dvtweatherapp.domain.weather.WeatherDisplayData

sealed class WeatherState {
    object Loading : WeatherState()
    data class Error(@StringRes val errorMessage: Int): WeatherState()
    data class Data(val data: WeatherDisplayData): WeatherState()
}
