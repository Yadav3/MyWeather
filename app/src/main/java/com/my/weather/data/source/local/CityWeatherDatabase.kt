package com.my.weather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.my.weather.data.source.local.convertor.WeatherConvertors
import com.my.weather.data.source.local.entity.CityWeatherData


@Database(entities = [CityWeatherData::class], version = 1)
@TypeConverters(WeatherConvertors::class)
abstract class CityWeatherDatabase : RoomDatabase() {

    abstract fun cityWeatherDao(): CityWeatherDao
}