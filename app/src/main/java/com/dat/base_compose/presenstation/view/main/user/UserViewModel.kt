package com.dat.base_compose.presenstation.view.main.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dat.base_compose.data.model.local.DarkThemeConfig
import com.dat.base_compose.data.respository.user.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModel() {
    val userUIState: StateFlow<UserUIState> = userDataRepository.userData.map { userData ->
        UserUIState.Success(userData.darkThemeConfig)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UserUIState.Loading,
    )

    fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.updateDarkMode(darkThemeConfig)
        }
    }
}

