package com.example.codingchallengeweather.data

import com.example.codingchallengeweather.data.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET

interface WeatherAPIEndpoints{

    // call the GET api endpoint for weather
    @GET(WeatherApiDetails.ENDPOINT_WEATHER_DETAILS)
    suspend fun getDetails(): WeatherModel
}