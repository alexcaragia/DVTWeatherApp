package com.android.dvtweatherapp.presentation.weather

import androidx.annotation.StringRes
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherDisplayData

sealed class WeatherState {
    object CurrentWeatherLoading : WeatherState()
    object ForecastWeatherLoading : WeatherState()
    data class Error(@StringRes val errorMessage: Int) : WeatherState()
    data class CurrentWeatherData(val data: WeatherDisplayData) : WeatherState()
    data class ForecastWeatherData(val data: List<ForecastDisplayData>) : WeatherState()
}
