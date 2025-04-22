package hu.gina.tkweatherapp.ui.forecast

import kotlinx.datetime.Instant

sealed class ForecastEvent {

    data class OnItemClicked(val itemTimeStamp: Instant, val itemName: String) : ForecastEvent()
}
