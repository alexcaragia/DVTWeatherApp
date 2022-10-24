package com.android.dvtweatherapp.presentation.weather.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.dvtweatherapp.common.animateWithFadeIn
import com.android.dvtweatherapp.domain.weather.WeatherType
import com.android.dvtweatherapp.presentation.weather.WeatherState
import kotlin.math.roundToInt

object BindingAdapter {
    private const val CELSIUS_SIGN = "\u2103"
    private const val MIN = "Min"
    private const val CURRENT = "Current"
    private const val MAX = "Max"

    @JvmStatic
    @BindingAdapter("android:isWeatherLoading")
    fun ProgressBar.bindCurrentWeatherLoading(state: WeatherState?) {
        visibility = if (state is WeatherState.CurrentWeatherLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("android:isForecastLoading")
    fun ProgressBar.bindForecastWeatherLoading(state: WeatherState?) {
        visibility = if (state is WeatherState.ForecastWeatherLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("android:forecastList")
    fun RecyclerView.bindForecastList(weatherState: WeatherState?) {
        if (weatherState is WeatherState.ForecastWeatherData) {
            (adapter as ForecastAdapter<*>).update((weatherState).data)
            animateWithFadeIn()
        }
    }

    @JvmStatic
    @BindingAdapter("android:weatherBackgroundColor")
    fun ViewGroup.bindBackgroundColor(weatherState: WeatherState?) {
        if (weatherState is WeatherState.CurrentWeatherData) {
            weatherState.data.weatherType?.backgroundColorRes?.let { setBackgroundResource(it) }
        }
    }

    @JvmStatic
    @BindingAdapter("android:weatherImage")
    fun ImageView.bindWeatherImage(weatherState: WeatherState?) {
        if (weatherState is WeatherState.CurrentWeatherData) {
            weatherState.data.weatherType?.backgroundImageRes?.let { setImageResource(it) }
            animateWithFadeIn()
        }
    }

    @JvmStatic
    @BindingAdapter("android:weatherText")
    fun TextView.bindWeather(weatherState: WeatherState?) {
        if (weatherState is WeatherState.CurrentWeatherData) {
            weatherState.data.weatherType?.weatherNameRes?.let { setText(it) }
            animateWithFadeIn()
        }
    }

    @JvmStatic
    @BindingAdapter("android:temperatureText")
    fun TextView.bindTemperature(weatherState: WeatherState?) {
        if (weatherState is WeatherState.CurrentWeatherData) {
            text = StringBuilder(
                weatherState.data.currentTemperature.roundToInt().toString()
            ).append(CELSIUS_SIGN).toString()
            animateWithFadeIn()
        }
    }

    @JvmStatic
    @BindingAdapter("android:weatherSrc")
    fun ImageView.bindWeatherSrc(weatherType: WeatherType) {
        setImageResource(weatherType.iconImageRes)
    }

    @JvmStatic
    @BindingAdapter("android:itemTemperatureText")
    fun TextView.bindTemperatureText(temperature: Float) {
        text = StringBuilder(temperature.roundToInt().toString()).append("\u2103").toString()
    }

    @JvmStatic
    @BindingAdapter("android:minTemperatureText")
    fun TextView.bindMinTemperature(weatherState: WeatherState?) {
        if (weatherState is WeatherState.CurrentWeatherData) {
            text = StringBuilder(weatherState.data.minTemperature.roundToInt().toString())
                .append(CELSIUS_SIGN)
                .appendLine()
                .append(MIN)
            animateWithFadeIn()
        }
    }

    @JvmStatic
    @BindingAdapter("android:currentTemperatureText")
    fun TextView.bindCurrentTemperature(weatherState: WeatherState?) {
        if (weatherState is WeatherState.CurrentWeatherData) {
            text = StringBuilder(weatherState.data.minTemperature.roundToInt().toString())
                .append(CELSIUS_SIGN)
                .appendLine()
                .append(CURRENT)
            animateWithFadeIn()
        }
    }

    @JvmStatic
    @BindingAdapter("android:maxTemperatureText")
    fun TextView.bindMaxTemperature(weatherState: WeatherState?) {
        if (weatherState is WeatherState.CurrentWeatherData) {
            text = StringBuilder(weatherState.data.minTemperature.roundToInt().toString())
                .append(CELSIUS_SIGN)
                .appendLine()
                .append(MAX)
            animateWithFadeIn()
        }
    }

    @JvmStatic
    @BindingAdapter("android:fadeIn")
    fun ViewGroup.bindFadeIn(weatherState: WeatherState?) {
        when (weatherState) {
            !is WeatherState.CurrentWeatherLoading, WeatherState.ForecastWeatherLoading -> animateWithFadeIn()
            else -> {
                // do nothing
            }
        }
    }
}
