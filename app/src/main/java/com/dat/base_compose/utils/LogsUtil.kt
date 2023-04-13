package com.dat.base_compose.utils

import timber.log.Timber

class LogsUtil : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "(${element.fileName}:${element.lineNumber}),${element.methodName}"
    }
}