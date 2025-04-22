package hu.gina.tkweatherapp.utils

@SuppressWarnings("MagicNumber")
fun getWindDirectionName(windDirection: Double): String? {
    return when {
        windDirection in 0.0..22.5 || windDirection > 337.5 -> "N"
        windDirection in 22.5..67.5 -> "NE"
        windDirection in 67.5..112.5 -> "E"
        windDirection in 112.5..157.5 -> "SE"
        windDirection in 157.5..202.5 -> "S"
        windDirection in 202.5..247.5 -> "SW"
        windDirection in 247.5..292.5 -> "W"
        windDirection in 292.5..337.5 -> "NW"
        else -> null
    }
}

@SuppressWarnings("MagicNumber")
fun getWindDirectionRotation(windDirection: Double): Float {
    return when {
        windDirection in 0.0..22.5 || windDirection > 337.5 -> -45f
        windDirection in 22.5..67.5 -> 0f
        windDirection in 67.5..112.5 -> 45f
        windDirection in 112.5..157.5 -> 90f
        windDirection in 157.5..202.5 -> 135f
        windDirection in 202.5..247.5 -> 190f
        windDirection in 247.5..292.5 -> -135f
        windDirection in 292.5..337.5 -> -90f
        else -> 0f
    }
}