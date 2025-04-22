package hu.gina.tkweatherapp.ui.forecast

import hu.gina.tkweatherapp.data.WeatherRepo
import hu.gina.tkweatherapp.ui.base.ViewModelBase
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_NAME
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_TIMESTAMP
import hu.gina.tkweatherapp.ui.utils.Routes
import hu.gina.tkweatherapp.ui.utils.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Instant

class ForecastViewModel(
    private val weatherRepo: WeatherRepo
) : ViewModelBase() {

    private val _weatherData = MutableStateFlow<Map<Instant, Pair<String, String>>>(emptyMap())
    val weatherData: StateFlow<Map<Instant, Pair<String, String>>> = _weatherData

    private val _location = MutableStateFlow<String>("")
    val location: StateFlow<String> = _location

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        getLocationAndWeatherDays()
    }

    fun onEvent(event: ForecastEvent) {
        when (event) {
            is ForecastEvent.OnItemClicked -> onItemClicked(event.itemTimeStamp, event.itemName)
        }
    }

    private fun onItemClicked(timestamp: Instant, dayName: String) {
        sendUiEvent(
            UiEvent.Navigate(
                Routes.Details.route +
                        "?$KEY_DAY_TIMESTAMP=$timestamp&" +
                        "$KEY_DAY_NAME=$dayName"
            )
        )
    }

    private fun getLocationAndWeatherDays() {
        startIoCoroutine {
            with(weatherRepo) {
                try {
                    updateWeatherData()
                    _location.value = getLocation()
                    _weatherData.value = getWeatherDays()
                } catch (e: Exception) {
                    _errorMessage.value = "Error!" //todo
                }
            }
        }
    }

}