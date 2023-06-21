package com.my.weather.data.source.remote

import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun downloadCitiesWeatherDataAndGetResponseBody() =
        apiService.downloadFileAndGetBodyResponse()
}