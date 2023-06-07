package com.dat.base_compose.core.common

import androidx.datastore.preferences.core.intPreferencesKey

object Constants {
    const val FLAVOR_DEVELOP = "development"
    const val FLAVOR_PRODUCT = "production"
    const val PREFERENCES_STORE_NAME = "DemoPref"

}

object DataStorePref {
    val PREF_DARK_MODE = intPreferencesKey("PREF_DARK_MODE")

    const val DARK_MODE_UN_ENABLE = 0
    const val DARK_MODE_ENABLE = 1
    const val DARK_MODE_SYSTEM = 2
}