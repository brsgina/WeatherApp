package hu.gina.tkweatherapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = PurpleLight,
    secondary = PurpleDark,
    tertiary = Turquoise80,
    error = ErrorRedLight,
    primaryContainer = PurpleTransparentLight,
    onPrimaryContainer = Color.White,
    onPrimary = Color.Black

)

private val LightColorScheme = lightColorScheme(
    primary = PurpleDark,
    secondary = PurpleLight,
    tertiary = Turquoise40,
    error = ErrorRed,
    primaryContainer = PurpleTransparentLight,
    onPrimaryContainer = Color.Black,
    onPrimary = Color.White
)

@Composable
fun TkWeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}