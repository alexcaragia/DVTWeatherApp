package com.android.dvtweatherapp.data.mappers

import com.android.dvtweatherapp.data.remote.ForecastDto
import com.android.dvtweatherapp.data.remote.WeatherDto
import com.android.dvtweatherapp.data.remote.WeatherInfo
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherType

fun WeatherDto.toWeatherDisplayData(): WeatherDisplayData {
    return WeatherDisplayData(
        this.temperatureData.currentTemperature.toString(),
        this.temperatureData.maxTemperature.toString(),
        this.temperatureData.minTemperature.toString(),
        this.weatherInformationList.toWeatherType()
    )
}

fun ForecastDto.toForecastDisplayData(): ForecastDisplayData {
    return ForecastDisplayData()
}

private fun List<WeatherInfo>.toWeatherType(): WeatherType? {
    return this.firstOrNull()?.name?.let { WeatherType.createFrom(it) }
}