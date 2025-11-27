package com.example.codingchallengeweather.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object WeatherModule {

    val api: WeatherAPIEndpoints by lazy {
        Retrofit.Builder()
            .baseUrl(WeatherApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPIEndpoints::class.java)
    }

}