package com.my.weather.ui.details

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
class CityWeatherDetailsViewModel @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository
) : ViewModel() {

    private var _cityWeatherDetails = MutableLiveData<Result<CityWeather>>()
    val cityWeatherDetails: LiveData<Result<CityWeather>> = _cityWeatherDetails

    private var cityWeatherDetailsJob: Job? = null

    fun getCityWeatherInfoByCityId(cityId: Int) {
        cityWeatherDetailsJob?.cancel()

        viewModelScope.launch(Dispatchers.IO) {
            _cityWeatherDetails.postValue(Result.Loading)
            val result = cityWeatherRepository.getCityWeatherByCityId(cityId)
            _cityWeatherDetails.postValue(Result.Success(result))
        }
    }
}