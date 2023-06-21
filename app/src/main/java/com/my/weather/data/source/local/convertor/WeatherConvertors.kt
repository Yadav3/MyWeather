package com.my.weather.data.source.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.my.weather.data.Weather

/**
 * Created by Ramesh
 * On: 21/06/23.
 */
class WeatherConvertors {

    @TypeConverter
    fun fromWeather(weather: List<Weather>): String {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun toWeather(json: String): List<Weather> {
        return Gson().fromJson(json, Array<Weather>::class.java).toList()
    }
}