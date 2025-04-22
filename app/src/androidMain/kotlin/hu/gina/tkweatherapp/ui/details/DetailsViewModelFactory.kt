package hu.gina.tkweatherapp.ui.details

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import hu.gina.tkweatherapp.apiservice.WeatherDependencies

class DetailsViewModelFactory : AbstractSavedStateViewModelFactory() {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(WeatherDependencies.provideRepository(), handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}