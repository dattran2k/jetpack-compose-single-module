package com.dat.base_compose.presenstation.view.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.respository.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {
    var curPage = 1
    var perPage = 10
    var endReached = false

    private val _homeUiState = MutableStateFlow(HomeUIState())
    val homeUiState = _homeUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeUIState(),
    )

    init {
        refreshData()
    }

    fun refreshData() {
        curPage = 1
        getTodos()
    }

    fun getTodos() {
        viewModelScope.launch {
            todoRepository.getTodos().collect {
                when (it) {
                    is Resource.Error -> {
                        _homeUiState.value = homeUiState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    Resource.Loading -> {
                        _homeUiState.value = homeUiState.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        curPage++
                        endReached = it.data.size < perPage
                        _homeUiState.value = homeUiState.value.copy(
                            isLoading = false,
                            listTodoItem = homeUiState.value.listTodoItem + it.data,
                        )
                    }
                }
            }
        }
    }
}
