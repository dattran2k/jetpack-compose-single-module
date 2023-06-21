package com.dat.base_compose.presenstation.view.main.home

import androidx.compose.runtime.Immutable
import com.dat.base_compose.data.model.TodoItem

@Immutable
data class HomeUIState(
    val listTodoItem: List<TodoItem> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEndReached : Boolean = false
)