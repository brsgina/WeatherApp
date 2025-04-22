package hu.gina.tkweatherapp.apiservice

sealed class WeatherResult {

    data class Success(val data: String) : WeatherResult()

    data class Failed(val code: Int): WeatherResult()

    data class Error(val exception: Throwable): WeatherResult()

}