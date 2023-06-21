package com.my.weather.data

import com.google.gson.Gson
import com.my.weather.data.source.local.LocalDataSource
import com.my.weather.data.source.local.entity.CityWeatherData
import com.my.weather.data.source.remote.RemoteDataSource
import com.my.weather.util.GZIPUtil
import com.my.weather.util.InternalStorageReadWriteUtil
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The repository to interact with the cities weather data
 *
 * @author Mohammad Abbas
 * @property localDataSource an instance of the [LocalDataSource] class
 * @property remoteDataSource an instance of the [RemoteDataSource] class
 * @property internalStorageReadWrite an instance of the [InternalStorageReadWriteUtil] class
 * @property gzipUtil an instance of the [GZIPUtil] class
 * @property mapper an instance of the [Mapper] class
 */
@Singleton
class CityWeatherRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val internalStorageReadWrite: InternalStorageReadWriteUtil,
    private val gzipUtil: GZIPUtil,
    private val mapper: Mapper
) {


    suspend fun getCitiesWeather(): List<CityWeather> {
        val citiesWeather = localDataSource.getCitiesWeatherData()

        return if (citiesWeather.isEmpty()) {
            updateCityWeatherDataInDb()
            mapper.run { localDataSource.getCitiesWeatherData().toCityWeather() }
        } else {
            mapper.run { citiesWeather.toCityWeather() }
        }
    }

    private suspend fun updateCityWeatherDataInDb() {

        val gzipInputStream =
            remoteDataSource.downloadCitiesWeatherDataAndGetResponseBody().body()?.byteStream()

        val citiesWeather = gzipInputStream?.let { inputStream ->
            val gZIPInputStream = gzipUtil.getGZIPInputStream(inputStream)

            val ndJsonFilePath =
                internalStorageReadWrite.writeFileToInternalStorage(gZIPInputStream)

            val ndJsonFile = internalStorageReadWrite.readFileFromInternalStorage(ndJsonFilePath)

            val jsonDataAsStringList = ndJsonFile.bufferedReader().readLines()

            jsonDataAsStringList.map { Gson().fromJson(it, CityWeather::class.java) }
        } ?: listOf()

        val mappedData = mapper.run { citiesWeather.toCityWeatherData() }
        localDataSource.storeCitiesWeatherDataInDb(mappedData)
    }

    suspend fun getCitiesWeatherByCityName(cityName: String): List<CityWeather> {
        return mapper.run { localDataSource.getCitiesWeatherByCityName(cityName).toCityWeather() }
    }

    suspend fun getCityWeatherByCityId(cityId: Int): CityWeather {
        return mapper.run { localDataSource.getCityWeatherByCityId(cityId).toCityWeather() }
    }
}