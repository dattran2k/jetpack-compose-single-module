package com.dat.base_compose.presenstation.view.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.respository.todo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    private val _homeUIState = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val homeUiState: StateFlow<HomeUIState> = _homeUIState

    init {
        getData()
    }

    fun updateData() {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            // This still have too much boilerplate code, how to improve this ?
            todoRepository.getDataWithFlow().map {
                when (it) {
                    is Resource.Error -> HomeUIState.Error(it.message)
                    Resource.Loading -> HomeUIState.Loading
                    is Resource.Success -> HomeUIState.Success(it.data)
                }
            }.collect {
                _homeUIState.emit(it)
            }
        }
    }
}
