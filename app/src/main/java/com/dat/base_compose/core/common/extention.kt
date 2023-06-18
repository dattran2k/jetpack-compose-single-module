package com.dat.base_compose.core.common

import android.view.View
import com.google.gson.Gson

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.visible(isVisible: Boolean) {
    this?.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun Any?.toJson(): String = if (this == null) "null" else Gson().toJson(this)


