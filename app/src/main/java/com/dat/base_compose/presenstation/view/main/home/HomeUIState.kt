package com.dat.base_compose.presenstation.view.main.home

import com.dat.base_compose.data.model.TodoItem


sealed interface HomeUIState {
    class Success(val data: List<TodoItem>) : HomeUIState
    class Error(val msg: String?) : HomeUIState
    object Loading : HomeUIState
}