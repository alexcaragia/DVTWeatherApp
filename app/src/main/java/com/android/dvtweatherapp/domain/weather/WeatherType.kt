package com.android.dvtweatherapp.domain.weather

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.dvtweatherapp.R
import com.android.dvtweatherapp.common.capitalize

enum class WeatherType(
    @StringRes val weatherNameRes: Int,
    @ColorRes val backgroundColorRes: Int,
    @ColorRes val statusBarColorRes: Int,
    @DrawableRes val backgroundImageRes: Int,
    @DrawableRes val iconImageRes: Int
) {
    CLEAR(
        R.string.label_weather_sunny,
        R.color.green,
        R.color.yellowStatusBar,
        R.drawable.ic_forest_sunny,
        R.drawable.ic_clear
    ),
    CLOUDS(
        R.string.label_weather_cloudy,
        R.color.gray,
        R.color.gray,
        R.drawable.ic_forest_cloudy,
        R.drawable.ic_partly_sunny
    ),
    RAIN(
        R.string.label_weather_rainy,
        R.color.darkGray,
        R.color.grayStatusBar,
        R.drawable.ic_forest_rainy,
        R.drawable.ic_rain
    );

    companion object {
        fun createFrom(name: String): WeatherType {
            return values().firstOrNull { it.name.lowercase().capitalize() == name } ?: RAIN
        }
    }
}
