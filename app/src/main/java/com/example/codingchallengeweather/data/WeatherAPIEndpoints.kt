package com.example.codingchallengeweather.data

import com.example.codingchallengeweather.data.model.WeatherReportItemModel
import retrofit2.http.GET

interface WeatherAPIEndpoints{
        @GET(WeatherApiDetails.ENDPOINT_WEATHER_DETAILS)
        suspend fun getWeatherReportDetails(): List<WeatherReportItemModel>
}