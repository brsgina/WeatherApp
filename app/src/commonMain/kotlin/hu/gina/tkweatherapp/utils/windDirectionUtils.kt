package hu.gina.tkweatherapp.utils

@SuppressWarnings("MagicNumber")
enum class WindDirections(val shortName: String?, val rotationDegrees: Float) {
    NORTH("N", -45f),
    NORTH_EAST("NE", 0f),
    EAST("E", 45f),
    SOUTH_EAST("SE", 90f),
    SOUTH("S",135f),
    SOUTH_WEST("SW", 190f),
    WEST("W", -135f),
    NORTH_WEST("NW", -90f),
    UNKNOWN(null, 0f)
}

@SuppressWarnings("MagicNumber")
private fun getWindDirection(windDirection: Double): WindDirections {
    return when {
        windDirection in 0.0..22.5 || windDirection > 337.5 -> WindDirections.NORTH
        windDirection in 22.5..67.5 -> WindDirections.NORTH_EAST
        windDirection in 67.5..112.5 -> WindDirections.EAST
        windDirection in 112.5..157.5 -> WindDirections.SOUTH_EAST
        windDirection in 157.5..202.5 -> WindDirections.SOUTH
        windDirection in 202.5..247.5 -> WindDirections.SOUTH_WEST
        windDirection in 247.5..292.5 -> WindDirections.WEST
        windDirection in 292.5..337.5 -> WindDirections.NORTH_WEST
        else -> WindDirections.UNKNOWN
    }
}

fun getWindDirectionName(windDirection: Double): String? = getWindDirection(windDirection).shortName

fun getWindDirectionRotation(windDirection: Double): Float = getWindDirection(windDirection).rotationDegrees