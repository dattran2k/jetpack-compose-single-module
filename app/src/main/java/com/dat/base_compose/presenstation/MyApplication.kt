package com.dat.base_compose.presenstation

import android.app.Application
import com.dat.base_compose.BuildConfig
import com.dat.base_compose.core.common.Constants
import com.dat.base_compose.core.util.InternetUtil
import com.dat.base_compose.core.util.LogsUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)
        if (BuildConfig.FLAVOR == Constants.FLAVOR_DEVELOP) {
            Timber.plant(LogsUtil())
        }
    }
}
