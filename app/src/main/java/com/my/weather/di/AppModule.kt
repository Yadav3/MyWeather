package com.my.weather.di

import android.content.Context
import androidx.room.Room
import com.my.weather.data.source.local.CityWeatherDao
import com.my.weather.data.source.local.CityWeatherDatabase
import com.my.weather.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton



@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCityWeatherDao(cityWeatherDatabase: CityWeatherDatabase): CityWeatherDao {
        return cityWeatherDatabase.cityWeatherDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CityWeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            CityWeatherDatabase::class.java,
            "city-weather-db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideAPIService(
        client: OkHttpClient,
    ): ApiService = createRetrofit(client).create(ApiService::class.java)

    private fun createRetrofit(client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("http://bulk.openweathermap.org/")
            .client(client)
            .build()
}