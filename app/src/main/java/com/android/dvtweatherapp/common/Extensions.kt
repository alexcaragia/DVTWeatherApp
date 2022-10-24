package com.android.dvtweatherapp.common

import android.view.View
import android.view.animation.AnimationUtils
import com.android.dvtweatherapp.R
import java.util.*

fun String.capitalize(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
}

fun View.animateWithFadeIn() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_weather_image))
}
