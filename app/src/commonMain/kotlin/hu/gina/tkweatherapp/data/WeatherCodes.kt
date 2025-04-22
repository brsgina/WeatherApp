package hu.gina.tkweatherapp.data

import kotlin.math.abs

enum class WeatherCode(val code: Int, val displayName: String) {
    UNKNOWN(0, "Unknown"),
    CLEAR(1000, "Clear, Sunny"),
    MOSTLY_CLEAR(1100, "Mostly Clear"),
    PARTLY_CLOUDY(1101, "Partly Cloudy"),
    MOSTLY_CLOUDY(1102, "Mostly Cloudy"),
    CLOUDY(1001, "Cloudy"),
    FOG(2000, "Fog"),
    LIGHT_FOG(2100, "Light Fog"),
    DRIZZLE(4000, "Drizzle"),
    RAIN(4001, "Rain"),
    LIGHT_RAIN(4200, "Light Rain"),
    HEAVY_RAIN(4201, "Heavy Rain"),
    SNOW(5000, "Snow"),
    FLURRIES(5001, "Flurries"),
    LIGHT_SNOW(5100, "Light Snow"),
    HEAVY_SNOW(5101, "Heavy Snow"),
    FREEZING_DRIZZLE(6000, "Freezing Drizzle"),
    FREEZING_RAIN(6001, "Freezing Rain"),
    LIGHT_FREEZING_RAIN(6200, "Light Freezing Rain"),
    HEAVY_FREEZING_RAIN(6201, "Heavy Freezing Rain"),
    ICE_PELLETS(7000, "Ice Pellets"),
    HEAVY_ICE_PELLETS(7101, "Heavy Ice Pellets"),
    LIGHT_ICE_PELLETS(7102, "Light Ice Pellets"),
    THUNDERSTORM(8000, "Thunderstorm");

    companion object {

        fun fromMinMaxCode(min: Int, max: Int): WeatherCode {
            val filteredNumbers = entries.filter { it.code in min..max }
            if (filteredNumbers.isEmpty()) return UNKNOWN

            val closestNumber = filteredNumbers.minByOrNull { abs(it.code - min) + abs(it.code - max) }
            return closestNumber ?: UNKNOWN
        }

    }
}
