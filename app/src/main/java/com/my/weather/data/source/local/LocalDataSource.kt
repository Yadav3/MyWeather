package com.my.weather.data.source.local

import com.my.weather.data.source.local.entity.CityWeatherData
import javax.inject.Inject


class LocalDataSource @Inject constructor(private val cityWeatherDao: CityWeatherDao) {


    suspend fun storeCitiesWeatherDataInDb(citiesWeather: List<CityWeatherData>) {
        cityWeatherDao.insertCitiesWeatherData(citiesWeather)
    }

    suspend fun getCitiesWeatherData(): List<CityWeatherData> {
        return cityWeatherDao.getCitiesWeatherData()
    }


    suspend fun getCitiesWeatherByCityName(cityName: String): List<CityWeatherData> {
        return cityWeatherDao.getCitiesWeatherByCityName(cityName)
    }


    suspend fun getCityWeatherByCityId(cityId: Int): CityWeatherData {
        return cityWeatherDao.getCityWeatherByCityId(cityId)
    }
}