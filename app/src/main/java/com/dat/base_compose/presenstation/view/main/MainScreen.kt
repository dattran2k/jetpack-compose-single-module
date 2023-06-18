package com.dat.base_compose.presenstation.view.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dat.base_compose.R
import com.dat.base_compose.presenstation.navigation.ScreenRoute
import com.dat.base_compose.presenstation.theme.CustomColorTheme
import com.dat.base_compose.presenstation.theme.PrimaryColor
import com.dat.base_compose.presenstation.view.detail.Detail
import com.dat.base_compose.presenstation.view.main.home.HomeScreen
import com.dat.base_compose.presenstation.view.main.home.HomeScreenRote
import com.dat.base_compose.presenstation.view.main.user.UserRoute
import com.dat.base_compose.presenstation.view.main.user.UserScreenRoute
object TrendScreenRoute : ScreenRoute("Trend")
object DiscoverScreenRoute : ScreenRoute("Discover")
object NotificationScreenRoute : ScreenRoute("Notification")
class MainItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val screenRoute: ScreenRoute
)

val tabItems = listOf(
    MainItem(R.drawable.ic_tab_home, R.string.tab_home, HomeScreenRote),
    MainItem(R.drawable.ic_tab_trend, R.string.tab_trend, TrendScreenRoute),
    MainItem(R.drawable.ic_tab_discover, R.string.tab_discover, DiscoverScreenRoute),
    MainItem(R.drawable.ic_tab_notification, R.string.tab_notification, NotificationScreenRoute),
    MainItem(R.drawable.ic_tab_user, R.string.tab_user, UserScreenRoute)
)

@Composable
fun MainRoute(onNavigateDetail: (arg : Detail.DetailScreenArg) -> Unit) {
    val navController = rememberNavController()
    MainScreen(navController, onNavigateDetail)
}

@Composable
fun MainScreen(navController: NavHostController, onNavigateDetail: (arg : Detail.DetailScreenArg) -> Unit) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                tabItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                            )
                        },
                        label = { Text(stringResource(screen.title)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.screenRoute.route } == true,
                        onClick = {
                            navController.navigate(screen.screenRoute.route) {
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
            startDestination = HomeScreenRote.route,
            Modifier.padding(innerPadding)
        ) {
            tabItems.forEachIndexed { index, mainItem ->
                composable(
                    route = mainItem.screenRoute.route
                ) {
                    when (index) {
                        0 -> HomeScreen(onNavigateDetail)
                        1 -> HomeScreen(onNavigateDetail)
                        else -> UserRoute()
                    }
                }

            }
        }
    }
}
