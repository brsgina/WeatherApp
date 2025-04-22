package hu.gina.tkweatherapp.ui.forecast

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import hu.gina.tkweatherapp.MainActivity
import hu.gina.tkweatherapp.apiservice.WeatherDependencies
import hu.gina.tkweatherapp.ui.utils.BigText
import hu.gina.tkweatherapp.ui.utils.SmallText
import hu.gina.tkweatherapp.ui.utils.UiEvent
import hu.gina.tkweatherapp.ui.utils.disableSplitMotionEvents
import hu.gina.tkweatherapp.ui.utils.setDefaultStyle
import kotlinx.datetime.Instant


@Composable
fun ForecastScreen(
    navController: NavHostController,
    activity: MainActivity
) {

    val factory = remember { ForecastViewModelFactory(WeatherDependencies.provideRepository()) }
    val viewModel: ForecastViewModel = viewModel(factory = factory)
    val items = viewModel.weatherData.collectAsState(initial = null)
    val locationName = viewModel.location.collectAsState(initial = "")

    BackHandler {
        activity.finish()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> navController.navigate(event.route)
                else -> { /* do nothing here */
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BigText("Weather forecast", verticalPadding = 16.dp)
        Row(modifier = Modifier.padding(bottom = 12.dp)) {
            Icon(Icons.Default.LocationOn, "Location")
            Text(locationName.value)
        }
        DayList(items, viewModel)
    }
}

@Composable
fun DayList(items: State<Map<Instant, Pair<String, String>>?>, viewModel: ForecastViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .disableSplitMotionEvents()
    ) {
        items(items = items.value?.toList() ?: emptyList()) { item ->
            DayItem(
                day = item,
                modifier = Modifier.setDefaultStyle {
                    viewModel.onEvent(
                        ForecastEvent.OnItemClicked(
                            item.first,
                            item.second.first
                        )
                    )
                }
            )
        }
    }
}

@Composable
fun DayItem(
    day: Pair<Instant, Pair<String, String>>,
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ) {
        SmallText(day.second.first, isBold = true)
        Spacer(modifier = Modifier.height(6.dp))
        SmallText(day.second.second)
    }
}
