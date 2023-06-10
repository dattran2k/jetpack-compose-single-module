package com.dat.base_compose.data.respository.user

import com.dat.base_compose.data.local.PreferenceDataSource
import com.dat.base_compose.data.model.local.DarkThemeConfig
import com.dat.base_compose.data.model.local.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val preferenceDataSource: PreferenceDataSource) :
    UserDataRepository {

    override val userData: Flow<UserData> = preferenceDataSource.userData

    override suspend fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        preferenceDataSource.updateDarkTheme(darkThemeConfig)
    }
}