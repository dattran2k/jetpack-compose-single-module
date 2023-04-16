package com.dat.base_compose

import android.app.Application
import com.dat.base_compose.common.Constants
import com.dat.base_compose.utils.InternetUtil
import com.dat.base_compose.utils.LogsUtil
import com.dat.base_compose.utils.PreferenceHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.getInstance().init(this)
        // TODO setup firebase
//        FirebaseApp.initializeApp(this)
        InternetUtil.init(this)
        if (BuildConfig.FLAVOR == Constants.FLAVOR_DEVELOP) {
//            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
            Timber.plant(LogsUtil())
        }
    }
}
