package com.lemnistech.weatherapp

import com.google.gson.Gson
import com.lemnistech.weatherapp.data.Result
import java.net.URL

class Request(private val url: String) {

    private fun getForecastJson() = URL(url).readText()

    fun getForecastResult(): Result = Gson().fromJson(getForecastJson(), Result::class.java)
}