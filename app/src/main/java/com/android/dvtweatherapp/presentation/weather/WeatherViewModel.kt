package com.android.dvtweatherapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dvtweatherapp.domain.location.LocationManager
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val repository: WeatherRepository,
    private val locationManager: LocationManager
) : ViewModel() {
    private val locationData: MutableLiveData<String> = MutableLiveData()
    val location: LiveData<String> = locationData

    fun loadLocation() {
        viewModelScope.launch {
            locationManager.getCurrentLocation()?.let {
                locationData.postValue("Location is: ${it.latitude};${it.longitude}")
            }
        }
    }
}
