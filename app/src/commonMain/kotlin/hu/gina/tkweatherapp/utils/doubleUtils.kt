package hu.gina.tkweatherapp.utils

fun Double.toWholeAndString(): String {
    return if (this % 1 == 0.0) {
        toInt().toString()
    } else toString()
}