package com.android.dvtweatherapp.presentation.weather

import androidx.lifecycle.*
import com.android.dvtweatherapp.domain.location.LocationTracker
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData
import com.android.dvtweatherapp.domain.weather.WeatherDisplayData
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {
    private val weatherStateData: MediatorLiveData<WeatherState> = MediatorLiveData()
    private val currentWeatherData: MutableLiveData<WeatherDisplayData?> = MutableLiveData()
    private val forecastWeatherData: MutableLiveData<List<ForecastDisplayData>?> = MutableLiveData()
    val weatherState: LiveData<WeatherState> = weatherStateData

    init {
        initializeDataSource()
    }

    fun loadWeather() {
        viewModelScope.launch {
            weatherStateData.postValue(WeatherState.CurrentWeatherLoading)
            weatherStateData.postValue(WeatherState.ForecastWeatherLoading)
            locationTracker.getCurrentLocation()?.let {
                currentWeatherData.postValue(
                    repository.getCurrentWeatherData(it.latitude, it.longitude).data
                )
                forecastWeatherData.postValue(
                    repository.getForecastWeatherData(it.latitude, it.longitude).data
                )
            }
        }
    }

    private fun initializeDataSource() {
        weatherStateData.addSource(currentWeatherData) { weatherData ->
            weatherStateData.postValue(
                weatherData?.let {
                    WeatherState.CurrentWeatherData(it)
                }
            )
        }
        weatherStateData.addSource(forecastWeatherData) { forecastDisplayDataList ->
            val groupedDays =
                forecastDisplayDataList?.groupBy { forecastData -> forecastData.weekDay }?.entries?.associate { entry ->
                    entry.key to entry.value.maxBy { it.temperatureData.maxTemperature }
                }
            weatherStateData.postValue(
                forecastDisplayDataList?.let {
                    groupedDays?.values?.toList()
                        ?.let { groupedDaysList -> WeatherState.ForecastWeatherData(groupedDaysList) }
                }
            )
        }
    }
}
