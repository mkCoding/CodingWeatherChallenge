package com.example.codingchallengeweather.data.model

data class WeatherReportItemModel(
    val city: String,
    val condition: String,
    val id: Int,
    val temperature: Int
)