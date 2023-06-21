package com.my.weather.util

import android.content.Context
import com.google.gson.Gson
import com.my.weather.data.CityWeather
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject



class JsonParser @Inject constructor(@ApplicationContext private val context: Context) {

    private val gson = Gson()

    fun parseJson(fileName: String): List<CityWeather> {
        // TODO: Make this function generic
        return readFileAsString(fileName).map { it.fromJson() }
    }



    private fun String.fromJson() = gson.fromJson(this, CityWeather::class.java)

  
    private fun readFileAsString(fileName: String): List<String> {
        var result = listOf<String>()

        try {
            result = context.assets.open(fileName)
                .bufferedReader()
                .readLines()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }
}