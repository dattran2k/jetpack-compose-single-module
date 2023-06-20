package com.dat.base_compose.presenstation.view.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.model.TodoItem
import com.dat.base_compose.data.respository.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    var todoList = MutableStateFlow<List<TodoItem>>(listOf())
    var isLoading = MutableStateFlow(false)
    var endReached = MutableStateFlow(false)
    var errorMessage = MutableStateFlow("")
    var curPage = MutableStateFlow(1)
    val homeUiState: StateFlow<HomeUIState> =
        combine(
            todoList,
            isLoading,
            errorMessage,
            endReached
        ) { todoItems, isLoading, errorMessage, endReached ->
            HomeUIState(todoItems, isLoading, errorMessage, endReached)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = HomeUIState(),
        )

    init {
        refreshData()
    }

    fun refreshData() {
        curPage.value = 1
        getTodos()
    }

    fun getTodos() {
        viewModelScope.launch {
            todoRepository.getTodos().collect {
                when (it) {
                    is Resource.Error -> {
                        isLoading.emit(false)
                        errorMessage.emit(it.message)
                    }

                    Resource.Loading -> {
                        isLoading.emit(true)
                    }

                    is Resource.Success -> {
                        isLoading.emit(false)
                        if (curPage.value == 1)
                            todoList.value = it.data
                        else
                            todoList.value += it.data
                        curPage.value++
                    }
                }
            }
        }
    }
}
