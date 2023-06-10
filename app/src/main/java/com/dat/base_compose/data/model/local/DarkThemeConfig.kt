package com.dat.base_compose.data.model.local

enum class DarkThemeConfig(val value: Int,val configName : String) {
    FOLLOW_SYSTEM(0,"By system"),
    LIGHT(1,"Light"),
    DARK(2,"Dark");

    companion object {
        infix fun from(value: Int?) = DarkThemeConfig.values().firstOrNull { it.value == value } ?: LIGHT
    }
}
