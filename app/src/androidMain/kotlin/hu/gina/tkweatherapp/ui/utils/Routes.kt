package hu.gina.tkweatherapp.ui.utils

sealed class Routes(val route: String) {

    data object Forecast : Routes("forecast")

    data object Details : Routes("details")
}