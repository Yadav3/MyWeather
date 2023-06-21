package com.my.weather.util

object WeatherUtil {


    fun Double.temperatureFromKelvinToCelsius(): Int {
        return (this - 273.15).toInt()
    }

    fun Double.speedFromMeterPerSecToKmPerHour(): Int {
        return (this * 3.6).toInt()
    }
}