package com.dat.base_compose.presenstation.view.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.respository.DemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(val demoRepository: DemoRepository) :
    ViewModel() {

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        getData()
    }

    fun updateData() {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            // This still have too much boilerplate code, how to improve this ?
            demoRepository.getDataWithFlow().map {
                when (it) {
                    is Resource.Error -> HomeUiState.Error(it.message)
                    Resource.Loading -> HomeUiState.Loading
                    is Resource.Success -> HomeUiState.Success(it.data)
                }
            }.collect {
                _homeUiState.emit(it)
            }
        }
    }
}
