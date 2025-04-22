package hu.gina.tkweatherapp.ui.utils

sealed class UiEvent {

    data class Navigate(val route: String) : UiEvent()

    data class ShowError(
        val message: String,
        val actionLabel: String? = null,
        val onPerformAction: suspend () -> Unit = {}
    ) : UiEvent()
}