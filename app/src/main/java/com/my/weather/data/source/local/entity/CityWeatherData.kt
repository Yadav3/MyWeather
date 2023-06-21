package com.my.weather.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.my.weather.data.Weather


@Entity(tableName = "city_weather")
data class CityWeatherData(
    @ColumnInfo(name = "city_id")
    @PrimaryKey
    val cityId: Int,
    @ColumnInfo(name = "city_name")
    val cityName: String,
    val country: String,
    val time: Double,
    val temperature: Double,
    @ColumnInfo(name = "temperature_min")
    val temperatureMin: Double,
    @ColumnInfo(name = "temperature_max")
    val temperatureMax: Double,
    val pressure: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weather: List<Weather>
)