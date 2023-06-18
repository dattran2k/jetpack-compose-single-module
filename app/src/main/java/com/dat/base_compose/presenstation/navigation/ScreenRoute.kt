package com.dat.base_compose.presenstation.navigation

import androidx.navigation.NavController

private const val DEEP_LINK_URI_PATTERN = "https://www.base_jetpack/"

abstract class ScreenRoute(val route: String) {
    val deepLink = DEEP_LINK_URI_PATTERN + route
    open fun navigate(navController: NavController) {
        navController.navigate(route)
    }
    // main


    // end main
}


