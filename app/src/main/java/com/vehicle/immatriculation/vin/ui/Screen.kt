package com.vehicle.immatriculation.vin.ui

sealed class Screen(val route: String, val name: String) {
    object Splash : Screen("splash", "Splash")

    object Home : Screen("home", "Home")

    object Settings : Screen("settings", "Settings")

    object help : Screen("help", "Help")

    object repport : Screen("repport", "Rapport")

    object VehicleDetail : Screen("vehicle/{plate}", "vehicle details") {
        fun route(recipeId: String) = "vehicle/$recipeId"

        const val ARG_PLATE: String = "plate"
    }
}
