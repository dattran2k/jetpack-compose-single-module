package com.dat.base_compose.presenstation.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dat.base_compose.presenstation.navigation.MainItem
import com.dat.base_compose.presenstation.navigation.MainTab
import com.dat.base_compose.presenstation.theme.CustomColorTheme
import com.dat.base_compose.presenstation.theme.PrimaryColor
import com.dat.base_compose.presenstation.view.main.home.HomeScreen
import com.dat.base_compose.presenstation.view.user.UserScreenRoute

@Composable
fun MainScreen(mainNavController: NavController) {
    val navController = rememberNavController()
    val items = listOf(
        MainItem.Home,
        MainItem.Trend,
        MainItem.Discover,
        MainItem.Notification,
        MainItem.User
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                            )
                        },
                        label = { Text(stringResource(screen.title)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.screen.route } == true,
                        onClick = {
                            navController.navigate(screen.screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                        selectedContentColor = PrimaryColor,
                        unselectedContentColor = CustomColorTheme.current.textTitle,
                        modifier = Modifier.background(CustomColorTheme.current.backGround)
                    )

                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = MainTab.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(MainTab.Home.route) { HomeScreen() }
            composable(MainTab.Trend.route) { UserScreenRoute() }
            composable(MainTab.Discover.route) { UserScreenRoute() }
            composable(MainTab.Notification.route) { UserScreenRoute() }
            composable(MainTab.User.route) { UserScreenRoute() }
        }
    }

}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: MainItem) =
    this?.hierarchy?.any {
        it.route?.contains(destination.screen.route, true) ?: false
    } ?: false