package com.dat.base_compose.presenstation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dat.base_compose.R

sealed class MainTab(val route: String) {
    object Home : Screen("Home")
    object Trend : Screen("Trend")
    object Discover : Screen("Discover")
    object Notification : Screen("Notification")
    object User : Screen("User")
}

sealed class MainItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val screen: Screen
) {
    object Home : MainItem(R.drawable.ic_tab_home, R.string.tab_home, MainTab.Home)
    object Trend : MainItem(R.drawable.ic_tab_trend, R.string.tab_trend, MainTab.Trend)
    object Discover : MainItem(R.drawable.ic_tab_discover, R.string.tab_discover, MainTab.Discover)
    object Notification :
        MainItem(R.drawable.ic_tab_notification, R.string.tab_notification, MainTab.Notification)

    object User : MainItem(R.drawable.ic_tab_user, R.string.tab_user, MainTab.User)
}