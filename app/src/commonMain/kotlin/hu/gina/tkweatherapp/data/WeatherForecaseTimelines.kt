package hu.gina.tkweatherapp.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastTimelines(
    val daily: List<TimelineItem<DataValuesDaily>>
)
