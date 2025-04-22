package hu.gina.tkweatherapp.ui.utils

sealed class UiEvent {

    data class Navigate(val route: String) : UiEvent()

    object PopBackStack : UiEvent()

    data class ShowError(val message: String) : UiEvent()
}