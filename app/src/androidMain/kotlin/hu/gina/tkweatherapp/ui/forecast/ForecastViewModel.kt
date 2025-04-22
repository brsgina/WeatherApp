package hu.gina.tkweatherapp.ui.forecast

import hu.gina.tkweatherapp.apiservice.WeatherResult
import hu.gina.tkweatherapp.data.WeatherRepo
import hu.gina.tkweatherapp.ui.base.ViewModelBase
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_NAME
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_TIMESTAMP
import hu.gina.tkweatherapp.ui.utils.Routes
import hu.gina.tkweatherapp.ui.utils.UiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Instant

class ForecastViewModel(
    private val weatherRepo: WeatherRepo
) : ViewModelBase() {

    private val _weatherData = MutableStateFlow<Map<Instant, Pair<String, String>>>(emptyMap())
    val weatherData: StateFlow<Map<Instant, Pair<String, String>>> = _weatherData

    private val _location = MutableStateFlow<String>("")
    val location: StateFlow<String> = _location

    private val _showProgressDialog = MutableStateFlow(false)
    val showProgressDialog: StateFlow<Boolean> = _showProgressDialog.asStateFlow()

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
        _showProgressDialog.value = true
        startIoCoroutine {
            with(weatherRepo) {
                when (val result= updateWeatherData()) {
                    is WeatherResult.Success -> {
                        _location.value = getLocation()
                        _weatherData.value = getWeatherDays()
                    }
                    is WeatherResult.Failed -> showErrorOnScreen(result.code)
                    is WeatherResult.Error -> showErrorOnScreen()
                }
                _showProgressDialog.value = false
            }
        }
    }

    private fun showErrorOnScreen(code: Int? = null) {
        sendUiEvent(UiEvent.ShowError(
            "Error${code?.let { "($it)" } ?: ""} during loading weather data!",
            "RETRY"
        ) {getLocationAndWeatherDays()})
    }

}