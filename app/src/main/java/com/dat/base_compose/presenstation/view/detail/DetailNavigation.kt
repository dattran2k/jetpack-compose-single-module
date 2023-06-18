package com.dat.base_compose.presenstation.view.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.dat.base_compose.data.model.TodoItem
import com.dat.base_compose.presenstation.navigation.ScreenRoute

const val DETAIL_NAME = "Detail"

const val DETAIL_ARG_ID = "DETAIL_ID"
const val DETAIL_ARG_TITLE = "DETAIL_TITLE"


object Detail : ScreenRoute("${DETAIL_NAME}/{$DETAIL_ARG_ID}/{$DETAIL_ARG_TITLE}") {

    data class DetailScreenArg(val id: Int, val title: String)

}

fun NavController.navigateDetail(todoItem: TodoItem) {
    this.navigate("$DETAIL_NAME/${todoItem.id}/${todoItem.title}")
}

fun NavGraphBuilder.detailScreen(
    onClickNavigate: () -> Unit
) {
    composable(
        route = Detail.route,
        deepLinks = listOf(
            navDeepLink { uriPattern = Detail.deepLink },
        ),
        arguments = listOf(
            navArgument(DETAIL_ARG_ID) {
                type = NavType.IntType
            },
            navArgument(DETAIL_ARG_TITLE) {
                type = NavType.StringType
            }
        )
    ) {
        val id = it.arguments?.getInt(DETAIL_ARG_ID) ?: -1
        val name = it.arguments?.getString(DETAIL_ARG_TITLE) ?: ""

        DetailRoute(onClickNavigate, Detail.DetailScreenArg(id, name))
    }
}
