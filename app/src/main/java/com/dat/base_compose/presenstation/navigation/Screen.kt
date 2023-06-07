package com.dat.base_compose.presenstation.navigation

sealed class Screen(val route: String) {

    object Main : Screen("Main")

}