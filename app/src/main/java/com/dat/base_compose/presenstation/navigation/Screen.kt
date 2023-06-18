package com.dat.base_compose.presenstation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

private const val DEEP_LINK_URI_PATTERN =
    "https://www.base_jetpack/"

sealed class Screen(private val inputRoute: String) {
    fun navigate(navController: NavController, navOptions: NavOptions? = null) {
        navController.navigate(inputRoute, navOptions)
    }

    private fun getPattern(): String {
        var result = ""
        setArguments().forEach {
            result += "/{${it.key}"
        }
        return result
    }

    val route = inputRoute + getPattern()
    fun getDeepLink() = DEEP_LINK_URI_PATTERN + route
    open fun setArguments() = mapOf<String, NavArgumentBuilder.() -> Unit>()


    object Main : Screen("Main")

    object Detail : Screen("Detail")

    // main
    object Home : Screen("Home")
    object Trend : Screen("Trend")
    object Discover : Screen("Discover")
    object Notification : Screen("Notification")
    object User : Screen("User")
    // end main
}

fun NavGraphBuilder.buildScreen(screen: Screen, screenRoute: @Composable () -> Unit) {
    composable(
        route = screen.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = screen.getDeepLink() },
        ),
        arguments = screen.setArguments().map {
            navArgument(it.key, it.value)
        }.toList()
    ) {
        screenRoute()
    }
}
