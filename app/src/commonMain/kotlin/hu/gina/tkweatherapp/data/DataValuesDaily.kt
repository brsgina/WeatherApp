package hu.gina.tkweatherapp.data

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class DataValuesDaily(
    val cloudBaseAvg: Double = 0.0,
    val cloudBaseMax: Double = 0.0,
    val cloudBaseMin: Double = 0.0,
    val cloudCeilingAvg: Double = 0.0,
    val cloudCeilingMax: Double = 0.0,
    val cloudCeilingMin: Double = 0.0,
    val cloudCoverAvg: Double = 0.0,
    val cloudCoverMax: Double = 0.0,
    val cloudCoverMin: Double = 0.0,
    val dewPointAvg: Double = 0.0,
    val dewPointMax: Double = 0.0,
    val dewPointMin: Double = 0.0,
    val evapotranspirationAvg: Double = 0.0,
    val evapotranspirationMax: Double = 0.0,
    val evapotranspirationMin: Double = 0.0,
    val evapotranspirationSum: Double = 0.0,
    val freezingRainIntensityAvg: Double = 0.0,
    val freezingRainIntensityMax: Double = 0.0,
    val freezingRainIntensityMin: Double = 0.0,
    val humidityAvg: Double = 0.0,
    val humidityMax: Double = 0.0,
    val humidityMin: Double = 0.0,
    val iceAccumulationAvg: Double = 0.0,
    val iceAccumulationLweAvg: Double = 0.0,
    val iceAccumulationLweMax: Double = 0.0,
    val iceAccumulationLweMin: Double = 0.0,
    val iceAccumulationMax: Double = 0.0,
    val iceAccumulationMin: Double = 0.0,
    val iceAccumulationSum: Double = 0.0,
    val moonriseTime: Instant? = null,
    val moonsetTime: Instant? = null,
    val precipitationProbabilityAvg: Double = 0.0,
    val precipitationProbabilityMax: Double = 0.0,
    val precipitationProbabilityMin: Double = 0.0,
    val pressureSurfaceLevelAvg: Double = 0.0,
    val pressureSurfaceLevelMax: Double = 0.0,
    val pressureSurfaceLevelMin: Double = 0.0,
    val rainAccumulationAvg: Double = 0.0,
    val rainAccumulationLweAvg: Double? = null,
    val rainAccumulationLweMax: Double? = null,
    val rainAccumulationLweMin: Double? = null,
    val rainAccumulationMax: Double = 0.0,
    val rainAccumulationMin: Double = 0.0,
    val rainAccumulationSum: Double = 0.0,
    val rainIntensityAvg: Double = 0.0,
    val rainIntensityMax: Double = 0.0,
    val rainIntensityMin: Double = 0.0,
    val sleetAccumulationAvg: Double = 0.0,
    val sleetAccumulationLweAvg: Double = 0.0,
    val sleetAccumulationLweMax: Double = 0.0,
    val sleetAccumulationLweMin: Double = 0.0,
    val sleetAccumulationMax: Double = 0.0,
    val sleetAccumulationMin: Double = 0.0,
    val sleetIntensityAvg: Double = 0.0,
    val sleetIntensityMax: Double = 0.0,
    val sleetIntensityMin: Double = 0.0,
    val snowAccumulationAvg: Double = 0.0,
    val snowAccumulationLweAvg: Double = 0.0,
    val snowAccumulationLweMax: Double = 0.0,
    val snowAccumulationLweMin: Double = 0.0,
    val snowAccumulationMax: Double = 0.0,
    val snowAccumulationMin: Double = 0.0,
    val snowAccumulationSum: Double = 0.0,
    val snowDepthAvg: Double? = null,
    val snowDepthMax: Double? = null,
    val snowDepthMin: Double? = null,
    val snowDepthSum: Double? = null,
    val snowIntensityAvg: Double = 0.0,
    val snowIntensityMax: Double = 0.0,
    val snowIntensityMin: Double = 0.0,
    val sunriseTime: Instant? = null,
    val sunsetTime: Instant? = null,
    val temperatureApparentAvg: Double = 0.0,
    val temperatureApparentMax: Double = 0.0,
    val temperatureApparentMin: Double = 0.0,
    val temperatureAvg: Double = 0.0,
    val temperatureMax: Double = 0.0,
    val temperatureMin: Double = 0.0,
    val uvHealthConcernAvg: Int? = null,
    val uvHealthConcernMax: Int? = null,
    val uvHealthConcernMin: Int? = null,
    val uvIndexAvg: Int? = null,
    val uvIndexMax: Int? = null,
    val uvIndexMin: Int? = null,
    val visibilityAvg: Double = 0.0,
    val visibilityMax: Double = 0.0,
    val visibilityMin: Double = 0.0,
    val weatherCodeMax: Int = 0,
    val weatherCodeMin: Int = 0,
    val windDirectionAvg: Double = 0.0,
    val windGustAvg: Double = 0.0,
    val windGustMax: Double = 0.0,
    val windGustMin: Double = 0.0,
    val windSpeedAvg: Double = 0.0,
    val windSpeedMax: Double = 0.0,
    val windSpeedMin: Double = 0.0
)