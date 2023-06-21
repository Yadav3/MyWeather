package com.my.weather.data.source.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {


    @GET("sample/weather_14.json.gz")
    suspend fun downloadFileAndGetBodyResponse(): Response<ResponseBody>
}