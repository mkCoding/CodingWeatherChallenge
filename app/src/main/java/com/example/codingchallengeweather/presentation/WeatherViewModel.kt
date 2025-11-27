package com.example.codingchallengeweather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallengeweather.data.model.WeatherReportItemModel
import com.example.codingchallengeweather.domain.WeatherApiRepository
import kotlinx.coroutines.launch

class WeatherViewModel(): ViewModel(){
    //initialize repo
    val repository = WeatherApiRepository()

    // data to grab data via state
    private val _weatherData = MutableLiveData<WeatherState>(WeatherState.Loading)
    val weatherData: LiveData<WeatherState> = _weatherData

    init { loadWeatherData() }

    fun loadWeatherData(){

        _weatherData.value = WeatherState.Loading

        viewModelScope.launch{
            try {
                val data = repository.getWeatherReportDetails()
               // val data = WeatherModel(city = "Atlanta", condition = "windy", temperature = 60)

                _weatherData.value = WeatherState.Success(weatherData = data)

            }catch(e:Exception){
                _weatherData.value = WeatherState.Error("error fetching data")
            }
        }

    }

//    fun getFilteredList(){
//        _wea
//    }
}

sealed class WeatherState{
    object Loading: WeatherState()
    data class Success(val weatherData: List<WeatherReportItemModel>): WeatherState()
    data class Error(val message:String): WeatherState()

}