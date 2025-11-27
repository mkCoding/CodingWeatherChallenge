package com.example.codingchallengeweather.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codingchallengeweather.data.model.WeatherReportItemModel
import okhttp3.internal.wait

@Composable
fun WeatherScreen(
    weatherState: WeatherState
) {

    // 272D2D
    val customHexColor = Color(0xFF00246B) // jetblack

    // the value user enters will constantly change (use var)
    var userEnteredCity by remember {mutableStateOf("")}

    Column(

        modifier = Modifier
            .background(customHexColor)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = Color.White,
                modifier = Modifier.padding(16.dp),
                fontStyle = FontStyle.Italic,
                text ="Find My Weather",
                fontSize = 40.sp
            )

            TextField(
                value = userEnteredCity,
                onValueChange = {userEnteredCity = it},
                placeholder = {Text("Enter your city")},
                singleLine = true,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
            )



        }

        when (weatherState) {
            is WeatherState.Loading -> {
                CircularProgressIndicator()
            }

            is WeatherState.Success -> {
                val allWeatherData = weatherState.weatherData

                val filteredData = allWeatherData.filter { item ->
                    item.city.contains(userEnteredCity, ignoreCase = true)
                }


                if (allWeatherData.isEmpty() && userEnteredCity.isEmpty()){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Data Not available",
                           // else "No city found matching '$userEnteredCity'",
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                    }
                }else if(filteredData.isEmpty()){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No city found matching '$userEnteredCity'",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
                else{
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // filtered data: data returned based on user input
                        items(filteredData) { item ->
                            WeatherReportCard(item = item)
                        }

                    }
                }
            }

            is WeatherState.Error -> {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(fontSize = 30.sp, text = "Error fetching data")

                }
            }
        }
    }

}

@Composable
fun WeatherReportCard(
    item: WeatherReportItemModel
) {
    val customHexColor = Color(0xFFF6F8FF) // ghost white
    Card(
        colors = CardDefaults.cardColors(
            containerColor = customHexColor, // Set your desired color here
        ),
        elevation = CardDefaults.cardElevation(40.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "${item.city}", fontSize = 16.sp)
                Text(text = "${item.condition}", fontSize = 16.sp)
                Text(text = "${item.temperature}", fontSize = 16.sp)
                //Text(text = "${item.icon}", fontSize = 16.sp)

            }

            // Right side: emoji icon
            Text(
                text = "${item.icon}",
                fontSize = 40.sp // increase size
            )


        }



    }

}


@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    val weatherStateSuccess = WeatherState.Success(
        listOf(
            WeatherReportItemModel(
                city = "Atlanta",
                condition = "windy",
                temperature = 60,
                id = 1,
                icon = "\uD83C\uDF2C\uFE0F"
            ),
            WeatherReportItemModel(
                city = "Lansing",
                condition = "cloudy",
                temperature = 60,
                id = 2,
                icon = "☁\uFE0F"
            ),
            WeatherReportItemModel(
                city = "New York",
                condition = "sunny",
                temperature = 90,
                id = 3,
                icon = "☀\uFE0F"
            )
        )
    )

    var errorstateWeather = WeatherState.Error("")
    WeatherScreen(weatherState = weatherStateSuccess)

}