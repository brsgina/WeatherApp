package hu.gina.tkweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hu.gina.tkweatherapp.ui.forecast.ForecastScreen
import hu.gina.tkweatherapp.ui.TkWeatherAppTheme
import hu.gina.tkweatherapp.ui.details.DetailsScreen
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_NAME
import hu.gina.tkweatherapp.ui.utils.KEY_DAY_TIMESTAMP
import hu.gina.tkweatherapp.ui.utils.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TkWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Forecast.route
                    ) {
                        addForecastScreen(navController)
                        addDetailsScreen(navController)
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.addForecastScreen(navController: NavHostController) {
        composable(Routes.Forecast.route) {
            ForecastScreen(navController, this@MainActivity)
        }
    }

    private fun NavGraphBuilder.addDetailsScreen(navController: NavHostController) {
        composable(
            route = Routes.Details.route +
                    "?$KEY_DAY_TIMESTAMP={dayTimestamp}&" +
                    "$KEY_DAY_NAME={dayName}",
            arguments = listOf(
                navArgument(name = "dayTimestamp") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "dayName") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            DetailsScreen(navController)
        }
    }
}