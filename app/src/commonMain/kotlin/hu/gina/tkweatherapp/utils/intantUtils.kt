package hu.gina.tkweatherapp.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import java.time.format.DateTimeFormatter

fun Instant.getDayDisplayName(): String {
    val tz = TimeZone.currentSystemDefault()
    val today = Clock.System.todayIn(tz)
    return when (val date = toLocalDateTime(tz).date) {
        today -> "Today"
        today.plus(DatePeriod(days = 1)) -> "Tomorrow"
        else -> date.dayOfWeek.name
            .lowercase()
            .replaceFirstChar { it.uppercase() }
    }
}

fun Instant.formattedDate(): String = toLocalDateTime(
    TimeZone.currentSystemDefault()
).date.toJavaLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

fun Instant.formattedTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    val localTime = this.toLocalDateTime(timeZone).time
    return localTime.toJavaLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
}

fun calculateFullDaySunProgress(
    sunrise: Instant?,
    sunset: Instant?
): Float {
    if (sunset == null || sunrise == null) return 0f
    val now = Clock.System.now()
    val timeZone = TimeZone.currentSystemDefault()

    val localDateTime = now.toLocalDateTime(timeZone)
    val localTime = localDateTime.time

    val minutesSinceMidnight = localTime.hour * 60 + localTime.minute
    val progress = minutesSinceMidnight / (24f * 60f) // progress: 0.0–1.0

    return progress
}
