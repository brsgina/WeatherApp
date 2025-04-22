package hu.gina.tkweatherapp.apiservice

import hu.gina.tkweatherapp.data.WeatherRepo

object WeatherDependencies {

    private val client = createHttpClient()
    private val apiService = WeatherApi(client)
    private val repository = WeatherRepo(apiService)

    fun provideRepository(): WeatherRepo = repository
}