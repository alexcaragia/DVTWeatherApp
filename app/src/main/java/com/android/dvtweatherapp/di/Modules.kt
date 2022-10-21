package com.android.dvtweatherapp.di

import com.android.dvtweatherapp.data.remote.WeatherApi
import com.android.dvtweatherapp.common.dispatchers.AppDispatchers
import com.android.dvtweatherapp.data.location.DefaultLocationManager
import com.android.dvtweatherapp.data.repository.DefaultWeatherRepository
import com.android.dvtweatherapp.domain.location.LocationManager
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import com.android.dvtweatherapp.presentation.weather.WeatherViewModel
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Dispatchers
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val LOCATION_REQUEST_INTERVAL = 10_000L
private const val LOCATION_REQUEST_MIN_INTERVAL = 5_000L

val appModule = module {
    single<WeatherApi> {
        Retrofit.Builder()
            .baseUrl(get<HttpUrl>())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }
    viewModel {
        WeatherViewModel(get(), get())
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
            .build()
    }
    single { BASE_URL.toHttpUrlOrNull() }
}

val repositoryModule = module {
    single<WeatherRepository> {
        DefaultWeatherRepository(get(named(AppDispatchers.IO)), get())
    }
}

val locationModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single {
        LocationRequest.Builder(Priority.PRIORITY_LOW_POWER, LOCATION_REQUEST_INTERVAL)
            .setMinUpdateIntervalMillis(LOCATION_REQUEST_MIN_INTERVAL)
            .build()
    }
    single<LocationManager> {
        DefaultLocationManager(
            get(),
            androidApplication()
        )
    }
}

val dispatchersModule = module {
    single(named(AppDispatchers.IO)) {
        Dispatchers.IO
    }
    single(named(AppDispatchers.Default)) {
        Dispatchers.Default
    }
    single(named(AppDispatchers.Main)) {
        Dispatchers.Main
    }
}
