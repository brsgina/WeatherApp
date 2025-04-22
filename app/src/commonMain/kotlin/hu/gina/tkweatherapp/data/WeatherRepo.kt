package hu.gina.tkweatherapp.data

import hu.gina.tkweatherapp.apiservice.WeatherApi
import hu.gina.tkweatherapp.apiservice.WeatherResult
import hu.gina.tkweatherapp.utils.formattedDate
import hu.gina.tkweatherapp.utils.getDayDisplayName
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class WeatherRepo(private val weatherApi: WeatherApi) {

    @OptIn(ExperimentalSerializationApi::class)
    private val jsonDecoder = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private var currentData: WeatherForecast? = null

    suspend fun updateWeatherData(): WeatherResult {
        val result = weatherApi.getWeather()
        if (result is WeatherResult.Success) {
            val data = jsonDecoder.decodeFromString(WeatherForecast.serializer(), result.data)
            currentData = data
        }
        return result
    }

    fun getLocation(): String {
        return currentData?.location?.name ?: ""
    }

    fun getWeatherDays(): Map<Instant, Pair<String, String>> {
        return currentData?.timelines?.daily?.associate {
            it.time to Pair(
                it.time.getDayDisplayName(),
                it.time.formattedDate()
            )
        } ?: emptyMap()
    }

    fun getWeatherDetails(day: String): DataValuesDaily? {
        return currentData?.timelines?.daily?.find { it.time == Instant.parse(day) }?.values
    }
}