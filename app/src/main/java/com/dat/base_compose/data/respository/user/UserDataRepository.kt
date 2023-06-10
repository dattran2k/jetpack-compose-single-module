package com.dat.base_compose.data.respository.user

import com.dat.base_compose.data.model.local.DarkThemeConfig
import com.dat.base_compose.data.model.local.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    suspend fun updateDarkMode(darkThemeConfig : DarkThemeConfig)

}