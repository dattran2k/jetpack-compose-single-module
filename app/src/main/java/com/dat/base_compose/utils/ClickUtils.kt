package com.dat.base_compose.utils

import android.os.Handler
import android.os.Looper
import timber.log.Timber

object ClickUtils {
    private var clickAble = true
    fun canClick(): Boolean {
        Timber.d(clickAble.toString())
        if (!clickAble)
            return false
        clickAble = false
        delayReset()
        return true
    }

    private fun delayReset() {
        Handler(Looper.getMainLooper()).postDelayed({ clickAble = true }, 800)
    }
}