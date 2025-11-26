package com.example.codingchallengeweather.domain

import com.example.codingchallengeweather.data.WeatherModule

class WeatherApiRepository {
    // api retrieval
    val api = WeatherModule.api

    // suspend function get Weather details
    suspend fun getWeatherDetails() = api.getDetails()

}