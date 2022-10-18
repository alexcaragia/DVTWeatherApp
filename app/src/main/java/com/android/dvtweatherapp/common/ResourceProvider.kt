package com.android.dvtweatherapp.common

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg args: String): String
    fun getProperty()
}

class DefaultResourceProvider(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg args: String): String {
        return context.getString(resId, *args)
    }

    override fun getProperty() {
    }
}
