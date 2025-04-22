package hu.gina.tkweatherapp.apiservice

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherApi(private val client: HttpClient) {

    companion object {
        private const val API_KEY = "Yj0jtL19zjYb5Nua3q0KgaPClUtCVJWf"
        private const val BASE_URL = "https://api.tomorrow.io/v4/weather/forecast"

        private const val URL_KEY_LOCATION = "location"
        private const val URL_KEY_API_KEY = "apikey"
        private const val URL_KEY_TIMESTEPS = "timesteps"

        private const val DEFAULT_LOCATION_VALUE = "dresden"
        private const val DEFAULT_TIMESTEPS_VALUE = "1d"

        private const val SUCCESS_HTTP_CODE = 200
    }

    suspend fun getWeather(): WeatherResult {
        return try {
            val response = client.get(BASE_URL) {
                url {
                    parameters.append(URL_KEY_LOCATION, DEFAULT_LOCATION_VALUE)
                    parameters.append(URL_KEY_API_KEY, API_KEY)
                    parameters.append(URL_KEY_TIMESTEPS, DEFAULT_TIMESTEPS_VALUE)
                }
            }
            Log.d(this::class.simpleName, "Weather query response: $response")
            return if (response.status.value == SUCCESS_HTTP_CODE) {
                WeatherResult.Success(response.body())
            } else {
                WeatherResult.Failed(response.status.value)
            }
        } catch (t: Throwable) {
            WeatherResult.Error(t)
        }
    }
}