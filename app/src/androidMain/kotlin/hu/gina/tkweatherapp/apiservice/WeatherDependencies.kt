package hu.gina.tkweatherapp.apiservice

import hu.gina.tkweatherapp.data.WeatherRepo
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.cookies.HttpCookies

object WeatherDependencies {

    private const val DEFAULT_TIMEOUT = 10_000

    private val client = HttpClient(Android) {
        engine {
            connectTimeout = DEFAULT_TIMEOUT
            socketTimeout = DEFAULT_TIMEOUT
        }
        install(HttpCookies)
    }
    private val apiService = WeatherApi(client)
    private val repository = WeatherRepo(apiService)

    fun provideRepository(): WeatherRepo = repository
}