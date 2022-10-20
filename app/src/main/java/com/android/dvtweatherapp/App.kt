package com.android.dvtweatherapp

import android.app.Application
import com.android.dvtweatherapp.di.appModule
import com.android.dvtweatherapp.di.locationModule
import com.android.dvtweatherapp.di.networkModule
import com.android.dvtweatherapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, networkModule, locationModule, repositoryModule)
        }
    }
}
