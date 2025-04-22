package hu.gina.tkweatherapp.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.gina.tkweatherapp.apiservice.WeatherDependencies

class ForecastViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(WeatherDependencies.provideRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}