package com.android.dvtweatherapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dvtweatherapp.domain.location.LocationManager
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val locationManager: LocationManager
) : ViewModel() {
    private val weatherStateData: MediatorLiveData<WeatherState> = MediatorLiveData()
    val weatherState: LiveData<WeatherState> = weatherStateData

    fun loadWeather() {
        viewModelScope.launch {
            locationManager.getCurrentLocation()?.let {
                val currentWeatherResult =
                    repository.getCurrentWeatherData(it.latitude, it.longitude)
                val forecastWeatherResult =
                    repository.getForecastWeatherData(it.latitude, it.longitude)
                println(currentWeatherResult)
                println(forecastWeatherResult)
            }
        }
    }
}
