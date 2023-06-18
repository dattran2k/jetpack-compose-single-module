package com.dat.base_compose.presenstation.view.main.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dat.base_compose.data.model.TodoItem
import com.dat.base_compose.presenstation.navigation.ScreenRoute
import com.dat.base_compose.presenstation.theme.BaseJetpackComposeTheme
import com.dat.base_compose.presenstation.theme.LocalCustomColorTheme

object HomeScreenRote : ScreenRoute("Home")

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeRoute(
    onNavigateDetail: (todo: TodoItem) -> Unit, viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUIState by viewModel.homeUiState.collectAsStateWithLifecycle()

    HomeScreen(homeUIState, onNavigateDetail, viewModel::loadData)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeUIState: HomeUIState,
    onNavigateDetail: (todo: TodoItem) -> Unit,
    getNewData: (isReset : Boolean) -> Unit
) {
    var refreshing by remember {
        mutableStateOf(false)
    }

    val pullRefreshState = rememberPullRefreshState(refreshing, {
        refreshing = false
        getNewData.invoke(true)
    })

    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {
        LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()) {
            val listTodo = homeUIState.listTodoItem
            val itemCount = listTodo.size
            items(itemCount) {
                val item = listTodo[it]
                if (it >= itemCount - 1 && !homeUIState.endReached && !homeUIState.isLoading) {
                    getNewData(false)
                }
                ItemTodo(item, Modifier.clickable {
                    onNavigateDetail.invoke(item)
                })
                if (homeUIState.isLoading && it == itemCount - 1)
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp)
                            .size(16.dp)
                            .wrapContentSize(Alignment.Center)
                    )
            }
        }
        if (homeUIState.isLoading && homeUIState.listTodoItem.isEmpty())
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .size(48.dp)
                    .wrapContentSize(Alignment.Center)

            )
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun ItemTodo(item: TodoItem, modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .background(
                shape = MaterialTheme.shapes.large,
                color = LocalCustomColorTheme.current.todoItemBackGround
            )
            .padding(16.dp),
    ) {
        Text(
            text = "${item.id}", style = MaterialTheme.typography.body1.copy(
                color = LocalCustomColorTheme.current.textTitle
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${item.title}", style = MaterialTheme.typography.body2.copy(
                color = LocalCustomColorTheme.current.textTitle
            )
        )
    }
}

@Preview
@Composable
private fun ItemTodoPreview() {
    BaseJetpackComposeTheme() {
        ItemTodo(
            item = TodoItem(
                false, 10, "Fake title", 10
            ),
        )
    }
}

@Preview
@Composable
private fun ListPreview() {
    BaseJetpackComposeTheme(true) {
        val homeUIState = HomeUIState(
            listOf(
                TodoItem(
                    false, 10, "Fake title", 10
                ),
                TodoItem(
                    false, 10, "Fake title", 10
                ),
                TodoItem(
                    false, 10, "Fake title", 10
                ),
                TodoItem(
                    false, 10, "Fake title", 10
                ),
            ),
            true,
            null,
            false,
        )
        HomeScreen(homeUIState, {}, {})
    }
}


@Preview
@Composable
private fun PreviewEmpty() {
    BaseJetpackComposeTheme {
        val homeUIState = HomeUIState(
            listOf(),
            true,
            null,
            false,
        )
        HomeScreen(homeUIState, {}, {})
    }
}
