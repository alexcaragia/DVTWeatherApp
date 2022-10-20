package com.android.dvtweatherapp.di

import com.android.dvtweatherapp.common.dispatchers.AppDispatchers
import com.android.dvtweatherapp.data.location.DefaultLocationManager
import com.android.dvtweatherapp.data.repository.DefaultWeatherRepository
import com.android.dvtweatherapp.domain.location.LocationManager
import com.android.dvtweatherapp.domain.repository.WeatherRepository
import com.android.dvtweatherapp.presentation.weather.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

val appModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single {
        Retrofit.Builder()
            .baseUrl(get<HttpUrl>())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    viewModel {
        WeatherViewModel(get(named(AppDispatchers.IO)),get(),get())
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                }
            )
            .build()
    }
    single { BASE_URL.toHttpUrlOrNull() }
}

val repositoryModule = module {
    single<WeatherRepository> {
        DefaultWeatherRepository()
    }
}

val locationModule = module {
    single<LocationManager> { DefaultLocationManager(get(), androidApplication()) }
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
