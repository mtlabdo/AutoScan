package com.vehicle.immatriculation.vin.navigation

import HomeScreen
import SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vehicle.immatriculation.vin.dispatcher.DispatcherProvider
import com.vehicle.immatriculation.vin.ui.Screen
import com.vehicle.immatriculation.vin.ui.screens.DetailScreen
import com.vehicle.immatriculation.vin.ui.screens.HelpScreen
import com.vehicle.immatriculation.vin.ui.screens.SettingsScreen
import com.vehicle.immatriculation.vin.utils.assistedViewModel
import com.vehicle.immatriculation.vin.view.viewmodel.DetailViewModel

const val RECIPES_NAV_HOST_ROUTE = "auto-scan-main-route"

@Composable
fun AppNavigation(dispatcherProvider: DispatcherProvider) {
    val navController = rememberNavController()

    val appState =
        remember {
            AppState(navController)
        }

    NavHost(
        navController,
        startDestination = Screen.Splash.route,
        route = RECIPES_NAV_HOST_ROUTE,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                appState = appState,
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = hiltViewModel(),
                coroutineDispatcher = dispatcherProvider,
                appState = appState,
            )
        }

        composable(
            Screen.VehicleDetail.route,
            arguments =
                listOf(
                    navArgument(Screen.VehicleDetail.ARG_PLATE) {
                        type = NavType.StringType
                    },
                ),
        ) {
            val plateNumber =
                requireNotNull(it.arguments?.getString(Screen.VehicleDetail.ARG_PLATE))

            // val plateNumber = "AA111AA"

            DetailScreen(
                viewModel =
                    assistedViewModel {
                        DetailViewModel.provideFactory(
                            detailViewModelFactory(),
                            plateNumber,
                        )
                    },
                coroutineDispatcher = dispatcherProvider,
                appState = appState,
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                viewModel = hiltViewModel(),
                appState = appState,
            )
        }

        composable(Screen.help.route) {
            HelpScreen(
                appState = appState,
            )
        }
    }
}

@Stable
class AppState(
    private val navController: NavController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigateToVehicleDetail(plate: String) = navController.navigate(Screen.VehicleDetail.route(plate))

    fun navigateToSettings() = navController.navigate(Screen.Settings.route)

    fun navToHelp() = navController.navigate(Screen.help.route)

    fun navigateToHome() {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun onBackClick() {
        navController.navigateUp()
    }

    fun addOnDestinationChangedListener(callback: NavController.OnDestinationChangedListener) {
        navController.addOnDestinationChangedListener(callback)
    }

    fun removeOnDestinationChangedListener(callback: NavController.OnDestinationChangedListener) {
        navController.removeOnDestinationChangedListener(callback)
    }
}
