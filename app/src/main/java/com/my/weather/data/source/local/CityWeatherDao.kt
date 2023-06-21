package com.my.weather.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.my.weather.data.source.local.entity.CityWeatherData


@Dao
interface CityWeatherDao {


    @Insert
    suspend fun insertCitiesWeatherData(citiesWeather: List<CityWeatherData>)


    @Query("SELECT * FROM city_weather")
    suspend fun getCitiesWeatherData(): List<CityWeatherData>


    @Query("SELECT * FROM city_weather WHERE city_name LIKE '%' || :cityName || '%'")
    suspend fun getCitiesWeatherByCityName(cityName: String): List<CityWeatherData>


    @Query("SELECT * FROM city_weather WHERE city_id = :cityId")
    suspend fun getCityWeatherByCityId(cityId: Int): CityWeatherData
}