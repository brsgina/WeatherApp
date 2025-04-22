package hu.gina.tkweatherapp.ui.details

import androidx.lifecycle.SavedStateHandle
import hu.gina.tkweatherapp.data.DataValuesDaily
import hu.gina.tkweatherapp.data.WeatherRepo
import hu.gina.tkweatherapp.ui.base.ViewModelBase
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_NAME
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_TIMESTAMP
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailsViewModel(
    private val weatherRepo: WeatherRepo,
    savedStateHandle: SavedStateHandle
) : ViewModelBase() {

    private val _weatherData = MutableStateFlow<DataValuesDaily?>(null)
    val weatherData: StateFlow<DataValuesDaily?> = _weatherData

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    init {
        savedStateHandle.get<String>(KEY_DAY_TIMESTAMP)?.let {
            loadWeatherData(it)
        }
        savedStateHandle.get<String>(KEY_DAY_NAME)?.let {
            _title.value = it
        }
    }

    private fun loadWeatherData(dayTimeStamp: String) {
        val data =  weatherRepo.getWeatherDetails(dayTimeStamp)
        _weatherData.value = data
    }

}