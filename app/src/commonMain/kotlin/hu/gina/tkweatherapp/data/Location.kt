package hu.gina.tkweatherapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val lat: Double? = null,
    val lon: Double? = null,
    val name: String? = null,
    val type: String? = null
)