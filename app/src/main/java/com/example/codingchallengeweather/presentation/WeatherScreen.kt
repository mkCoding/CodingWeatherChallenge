package com.example.codingchallengeweather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codingchallengeweather.data.model.WeatherModel

@Composable
fun WeatherScreen(
    weatherState: WeatherState
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        when(weatherState){
            is WeatherState.Loading->{
                CircularProgressIndicator()
            }
            is WeatherState.Success -> {
                Text(text = "${weatherState.weatherData.city}", fontSize = 16.sp)
                Text(text = "${weatherState.weatherData.condition}", fontSize = 16.sp)
                Text(text = "${weatherState.weatherData.temperature}", fontSize = 16.sp)


            }
            is WeatherState.Error -> {
                Text(fontSize = 30.sp, text = "error fetching Data")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview(){
    val weatherStateSuccess = WeatherState.Success(
        weatherData = WeatherModel(
            city = "Atlanta",
            condition = "windy",
            temperature = 60
        )
    )
    WeatherScreen(weatherState = weatherStateSuccess)
}