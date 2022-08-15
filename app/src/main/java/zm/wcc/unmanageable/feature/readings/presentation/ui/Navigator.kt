package zm.wcc.unmanageable.feature.readings.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import zm.wcc.unmanageable.feature.readings.presentation.components.MainScreen
import zm.wcc.unmanageable.feature.readings.presentation.components.ReadingDataItemDetail
import zm.wcc.unmanageable.feature.readings.presentation.viewmodel.ReadingsViewModel

@Composable
fun Navigator(viewModel: ReadingsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel)

        }
        composable(
            route = Screen.DetailScreen.route + "/{title}/{reading}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument("reading") {
                    type = NavType.StringType
                    nullable = false
                }

            )
        ) { readingData ->
            ReadingDataItemDetail(
                navController = navController,
                title = readingData.arguments?.getString("title"),
                reading = readingData.arguments?.getString("reading"))

        }
    }
}