package com.android.dvtweatherapp.domain.location

import android.location.Location

interface LocationManager {
    suspend fun getCurrentLocation(): Location?
}
