package com.android.dvtweatherapp.di

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

val appModule = module {
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
    single {
        Retrofit.Builder()
            .baseUrl(get<HttpUrl>())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single { BASE_URL.toHttpUrlOrNull() }
}

val repositoryModule = module {
}

val locationModule = module {
}

val dispatchersModule = {
}
