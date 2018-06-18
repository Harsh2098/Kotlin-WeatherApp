package com.lemnistech.weatherapp.data

data class Result(val city: City, val list: List<Forecast>)

data class City(val name: String, val country: String)

data class Forecast(val temp: Temperature, val weather: ArrayList<Weather>, val dt: Long)

data class Temperature(val min: Double, val max: Double)

data class Weather(val main: String, val description: String, val icon: String)
