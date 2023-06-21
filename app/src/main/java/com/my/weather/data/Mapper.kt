package com.my.weather.data

import com.my.weather.data.source.local.entity.CityWeatherData
import javax.inject.Inject

/**
 * Contain mapper functions
 *
 * @author Mohammad Abbas
 */
class Mapper @Inject constructor() {


    fun List<CityWeather>.toCityWeatherData() = map {
        CityWeatherData(
            cityId = it.city.id,
            cityName = it.city.name,
            country = it.city.country,
            time = it.time,
            temperature = it.main.temp,
            temperatureMin = it.main.tempMin,
            temperatureMax = it.main.tempMax,
            pressure = it.main.pressure,
            humidity = it.main.humidity,
            windSpeed = it.wind.speed,
            weather = it.weather
        )
    }


    fun List<CityWeatherData>.toCityWeather() = map { it.toCityWeather() }

    fun CityWeatherData.toCityWeather() = CityWeather(
        city = City(
            id = cityId,
            name = cityName,
            findname = "",
            country = country,
            coord = Coord(0.0, 0.0),
            zoom = 0
        ),
        time = time,
        main = Main(
            temp = temperature,
            pressure = pressure,
            humidity = humidity,
            tempMin = temperatureMin,
            tempMax = temperatureMax
        ),
        wind = Wind(
            speed = windSpeed,
            deg = 0.0,
            varBeg = 0.0,
            varEnd = 0.0
        ),
        clouds = Clouds(0.0),
        weather = weather
    )
}