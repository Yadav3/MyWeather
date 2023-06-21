package com.my.weather.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.weather.data.CityWeather
import com.my.weather.data.CityWeatherRepository
import com.my.weather.data.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityWeatherListViewModel @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) :
    ViewModel() {

    private var _citiesWeather = MutableLiveData<Result<List<CityWeather>>>()
    val citiesWeather: LiveData<Result<List<CityWeather>>> = _citiesWeather

    fun getCitiesWeather() {
        val cashedData = getCachedData()
        if (cashedData.isNotEmpty()) {
            _citiesWeather.value = Result.Success(cashedData)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _citiesWeather.postValue(Result.Loading)
            val result = cityWeatherRepository.getCitiesWeather()
            _citiesWeather.postValue(Result.Success(result))
        }
    }


    private fun getCachedData(): List<CityWeather> {
        _citiesWeather.value?.let { result ->
            if (result is Result.Success) {
                return result.data
            }
        }

        return emptyList()
    }

    private var _searchedCitiesWeather = MutableLiveData<Result<List<CityWeather>>>()
    val searchedCitiesWeather: LiveData<Result<List<CityWeather>>> = _searchedCitiesWeather

    private var searchJob: Job? = null

   
    fun searchByCityName(name: String) {
        searchJob?.cancel()

        if (name.isBlank()) {
            _searchedCitiesWeather.value = _citiesWeather.value
            return
        }

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            _searchedCitiesWeather.postValue(Result.Loading)
            val result = cityWeatherRepository.getCitiesWeatherByCityName(name.trim())
            _searchedCitiesWeather.postValue(Result.Success(result))
        }
    }
}