package hu.gina.tkweatherapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import hu.gina.tkweatherapp.R
import hu.gina.tkweatherapp.data.DataValuesDaily
import hu.gina.tkweatherapp.data.WeatherCode
import hu.gina.tkweatherapp.ui.componenets.BigText
import hu.gina.tkweatherapp.ui.componenets.DataRow
import hu.gina.tkweatherapp.ui.componenets.MediumText
import hu.gina.tkweatherapp.ui.componenets.SmallText
import hu.gina.tkweatherapp.ui.componenets.TinyText
import hu.gina.tkweatherapp.ui.utils.setDefaultStyle
import hu.gina.tkweatherapp.utils.calculateFullDaySunProgress
import hu.gina.tkweatherapp.utils.formattedTime
import hu.gina.tkweatherapp.utils.getWindDirectionName
import hu.gina.tkweatherapp.utils.getWindDirectionRotation
import hu.gina.tkweatherapp.utils.toWholeAndString

@Composable
fun DetailsScreen(
    navController: NavHostController
) {

    val viewModel: DetailsViewModel = viewModel(factory = DetailsViewModelFactory())
    val details = viewModel.weatherData.collectAsState()

    Column {
        Row(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
            }
            Spacer(modifier = Modifier.width(12.dp))
            BigText(viewModel.title.collectAsState().value)
        }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            details.value?.let {
                InfoCard(it)
                RainCard(it)
                WindCard(it)
                UvCard(it)
                SunCard(it)
            }
        }
    }

}

@Composable
fun InfoCard(data: DataValuesDaily) {
    Column(modifier = Modifier.setDefaultStyle()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BigText(
                WeatherCode.fromMinMaxCode(
                    data.weatherCodeMin,
                    data.weatherCodeMax
                ).displayName
            )
            Button(
                onClick = { /*do nothing for now*/ }
            ) {
                Icon(Icons.Outlined.Info, contentDescription = "Info")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Info")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                BigText("${data.temperatureMax.toWholeAndString()}°C")
                TinyText("Highest value", isBold = true)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                MediumText("${data.temperatureMin.toWholeAndString()}°C")
                TinyText("Lowest value", isBold = true)
            }
        }
    }
}

@Composable
fun RainCard(data: DataValuesDaily) {
    Column(modifier = Modifier.setDefaultStyle()) {
        DataRow(
            "Average probability of rain",
            (data.precipitationProbabilityAvg * 100).toWholeAndString(),
            "%"
        )
        DataRow("Average humidity", data.humidityAvg.toWholeAndString(), "%")
        LinearProgressIndicator(
            progress = { data.humidityAvg.toFloat() / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
    }

}

@Composable
fun WindCard(data: DataValuesDaily) {
    Column(modifier = Modifier.setDefaultStyle()) {
        DataRow("Maximum wind speed", data.windSpeedMax.toWholeAndString(), "km/h")
        SmallText("Wind direction")
        getWindDirectionName(data.windDirectionAvg)?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.near_me_icon),
                    contentDescription = "Wind",
                    modifier = Modifier
                        .size(54.dp)
                        .rotate(getWindDirectionRotation(data.windDirectionAvg))
                        .padding(start = 4.dp)
                )
                SmallText(it)
            }
        }

    }
}

@Composable
fun UvCard(data: DataValuesDaily) {
    Column(modifier = Modifier.setDefaultStyle()) {
        DataRow("Maximum UV index", data.uvIndexMax?.toString(), null)
        DataRow("Average visibility", data.visibilityAvg.toWholeAndString(), "m")
        DataRow("Average air pressure", data.pressureSurfaceLevelAvg.toWholeAndString(), "mbar")
    }

}

@Composable
fun SunCard(data: DataValuesDaily) {
    Column(modifier = Modifier.setDefaultStyle()) {
        SmallText("Sun position")
        SunPath(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            progress = calculateFullDaySunProgress(data.sunriseTime, data.sunsetTime)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text("Sunrise")
                data.sunriseTime?.formattedTime()?.let {
                    Text("$it Uhr")
                }
            }
            Spacer(Modifier.width(24.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Sunset")
                data.sunsetTime?.formattedTime()?.let {
                    Text("$it Uhr")
                }
            }
        }
    }

}