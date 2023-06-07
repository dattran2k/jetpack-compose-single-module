package com.dat.base_compose.presenstation.view.main.home

import com.dat.base_compose.data.model.TodoItem


sealed interface HomeUiState {
    class Success(val data: List<TodoItem>) : HomeUiState
    class Error(val msg: String?) : HomeUiState
    object Loading : HomeUiState
}