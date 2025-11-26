package com.example.codingchallengeweather.data.model

//import com.google.gson.annotations.SerializedName

data class WeatherModel(
    val city: String? = "",
    val condition: String? = "",
    val temperature: Int? = 0
)