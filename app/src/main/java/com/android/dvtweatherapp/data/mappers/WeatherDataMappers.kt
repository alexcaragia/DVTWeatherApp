package com.android.dvtweatherapp.data.mappers

import com.android.dvtweatherapp.data.remote.ForecastDto
import com.android.dvtweatherapp.data.remote.WeatherDto
import com.android.dvtweatherapp.domain.weather.*
import java.text.SimpleDateFormat
import java.util.*

private const val WEEK_DAY_DATE_FORMAT = "EEEE"
private const val UNIX_TIMESTAMP_MULTIPLIER = 1_000L

fun WeatherDto.toWeatherDisplayData(): WeatherDisplayData {
    return WeatherDisplayData(
        temperatureData.currentTemperature,
        temperatureData.maxTemperature,
        temperatureData.minTemperature,
        this.weatherInformationList.firstOrNull()?.name?.let { WeatherType.createFrom(it) }
    )
}

fun ForecastDto.toForecastDisplayDataList(): List<ForecastDisplayData> {
    val forecastDisplayDataMap = this.forecastInfoList.associate {
        it.timestamp to it.weatherInformationList.firstOrNull().let { weatherInfo ->
            ForecastDisplayData(
                it.timestamp.formatDate(),
                WeatherType.createFrom(weatherInfo?.name.toString()),
                it.temperatureData
            )
        }
    }
    return forecastDisplayDataMap.values.toList()
}

fun Long.formatDate(): String {
    return SimpleDateFormat(
        WEEK_DAY_DATE_FORMAT,
        Locale.getDefault()
    ).format(Date(this * UNIX_TIMESTAMP_MULTIPLIER))
}
