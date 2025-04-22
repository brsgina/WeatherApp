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

private const val MINUTES_OF_HOUR = 60
private const val HOURS_OF_DAY = 24f

fun calculateFullDaySunProgress(
    sunrise: Instant?,
    sunset: Instant?
): Float {
    if (sunset == null || sunrise == null) return 0f
    val timeZone = TimeZone.currentSystemDefault()
    val localTime = Clock.System.now().toLocalDateTime(timeZone).time

    val minutesSinceMidnight = localTime.hour * MINUTES_OF_HOUR + localTime.minute
    val progress = minutesSinceMidnight / (HOURS_OF_DAY * MINUTES_OF_HOUR.toFloat()) // progress: 0.0–1.0

    return progress
}
