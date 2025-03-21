package com.noby.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noby.navigation.graph.DetailScreens
import com.noby.navigation.graph.detailGraph
import com.noby.navigation.screens.Detail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    homeScreen: @Composable () -> Unit,
    detailScreen: @Composable (Boolean) -> Unit,
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navigator.actions.collectLatest { action ->
            when (action) {
                Navigator.Action.Back -> navController.popBackStack()
                is Navigator.Action.Navigate -> navController.navigate(
                    route = action.destination,
                    builder = action.navOptions
                    )
             }
        }
    }

    NavHost(navController, startDestination = Destination.home.route) {
        composable(Destination.home.route) {
            homeScreen()
        }
    }

}
