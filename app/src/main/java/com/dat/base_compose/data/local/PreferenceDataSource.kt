package com.dat.base_compose.data.local

import com.dat.base_compose.core.common.DataStorePref
import com.dat.base_compose.core.datastore.DataStoreManager
import com.dat.base_compose.data.model.local.DarkThemeConfig
import com.dat.base_compose.data.model.local.UserData
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class PreferenceDataSource @Inject constructor(private val dataStoreManager: DataStoreManager) {
    val userData = dataStoreManager.userData
    suspend fun updateDarkTheme(darkThemeConfig: DarkThemeConfig) {
        dataStoreManager.storeValue(DataStoreManager.PREF_DARK_MODE, darkThemeConfig.value)
    }
}