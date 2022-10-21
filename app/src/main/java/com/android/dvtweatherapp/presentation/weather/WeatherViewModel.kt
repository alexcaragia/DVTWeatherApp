package com.android.dvtweatherapp.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dvtweatherapp.domain.location.LocationManager
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
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
