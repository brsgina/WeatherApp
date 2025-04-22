package hu.gina.tkweatherapp.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecast(
    val location: Location,
    val timelines: WeatherForecastTimelines
)