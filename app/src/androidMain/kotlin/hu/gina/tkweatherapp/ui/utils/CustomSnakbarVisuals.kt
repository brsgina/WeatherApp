package hu.gina.tkweatherapp.ui.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.graphics.Color

data class CustomSnackBarVisuals(
    override val message: String,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    val containerColor: Color = Color.Black,
    val contentColor: Color = Color.White,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = true
) : SnackbarVisuals