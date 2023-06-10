package com.dat.base_compose.presenstation.view.user

import com.dat.base_compose.data.model.local.DarkThemeConfig

sealed interface UserUIState {
    object Loading : UserUIState
    data class Success(val darkMode: DarkThemeConfig) : UserUIState
}
