package com.example.codingchallengeweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codingchallengeweather.presentation.WeatherScreen
import com.example.codingchallengeweather.presentation.WeatherState
import com.example.codingchallengeweather.presentation.WeatherViewModel
import com.example.codingchallengeweather.ui.theme.CodingChallengeWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodingChallengeWeatherTheme {

                val viewmodel: WeatherViewModel = viewModel()
                val weatherState by viewmodel.weatherData.observeAsState(WeatherState.Loading)
                WeatherScreen(weatherState = weatherState)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CodingChallengeWeatherTheme {
//        Greeting("Android")
//    }
//}