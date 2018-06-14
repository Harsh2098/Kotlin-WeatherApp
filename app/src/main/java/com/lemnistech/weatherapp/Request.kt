package com.lemnistech.weatherapp

import android.util.Log
import java.net.URL

class Request(private val url: String) {

    fun getForecastJson(): String {
        val forecastJsonString = URL(url).readText()
        Log.d(":::", forecastJsonString)
        return forecastJsonString
    }
}