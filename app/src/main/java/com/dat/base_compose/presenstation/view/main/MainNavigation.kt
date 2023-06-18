package com.dat.base_compose.presenstation.view.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.dat.base_compose.presenstation.navigation.ScreenRoute
import com.dat.base_compose.presenstation.view.detail.Detail

const val MAIN_NAME = "Main"

object MainScreenRoute : ScreenRoute(MAIN_NAME)

fun NavController.navigateMain() {
    navigate(MainScreenRoute.route)
}

fun NavGraphBuilder.mainScreen(
    onNavigateDetail: (arg : Detail.DetailScreenArg) -> Unit
) {
    composable(
        route = MainScreenRoute.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = MainScreenRoute.deepLink },
        ),
    ) {
        MainRoute(onNavigateDetail)
    }
}
