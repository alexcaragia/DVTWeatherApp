package com.android.dvtweatherapp.domain.weather

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.dvtweatherapp.R
import com.android.dvtweatherapp.common.capitalize

enum class WeatherType(
    @StringRes weatherNameRes: Int,
    @ColorRes backgroundColorRes: Int,
    @DrawableRes backgroundImageRes: Int
) {
    SUNNY(R.string.label_weather_sunny, R.color.green, R.drawable.ic_forest_sunny),
    CLOUDY(R.string.label_weather_cloudy, R.color.gray, R.drawable.ic_forest_cloudy),
    RAINY(R.string.label_weather_rainy, R.color.darkGray, R.drawable.ic_forest_rainy);

    companion object {
        fun createFrom(name: String): WeatherType {
            return values().firstOrNull { it.name.capitalize() == name } ?: RAINY
        }
    }
}
