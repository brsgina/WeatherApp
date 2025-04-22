package hu.gina.tkweatherapp.data

import kotlinx.serialization.Serializable
import kotlinx.datetime.Instant

@Serializable
data class TimelineItem<out E>(val time: Instant, val values: E)