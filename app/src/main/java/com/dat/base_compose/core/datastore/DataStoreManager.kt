package com.dat.base_compose.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dat.base_compose.core.common.Constants
import com.dat.base_compose.data.model.local.DarkThemeConfig
import com.dat.base_compose.data.model.local.UserData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        val PREF_DARK_MODE = intPreferencesKey("PREF_DARK_MODE")
        val TEST = intPreferencesKey("TEST")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.PREFERENCES_STORE_NAME)

    suspend fun <T> storeValue(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    fun <T> getValue(key: Preferences.Key<T>): Flow<T?> = context.dataStore.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map { preferences ->
        preferences[key]
    }

    val userData = combine(getValue(PREF_DARK_MODE), getValue(TEST)) { darkMode, test ->
        UserData(DarkThemeConfig.from(darkMode), test ?: 0)
    }.distinctUntilChanged()

}