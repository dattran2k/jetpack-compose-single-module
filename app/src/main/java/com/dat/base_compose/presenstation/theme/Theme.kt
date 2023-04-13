package com.dat.base_compose.presenstation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

object ThemeState {
    var isDark by mutableStateOf(false)
}

data class MyColorPalette(
    val HomeBackGround: Color,
    val homeTextTitle : Color
)

val DarkTheme = MyColorPalette(BlackColor, WhiteColor)
val LightTheme = MyColorPalette(WhiteColor, BlackColor)
val LocalCustomColorTheme = compositionLocalOf {
    LightTheme
}
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)


@Composable
fun BaseJetpackComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val colorsCustom = if (darkTheme) {
        DarkTheme
    } else {
        LightTheme
    }
    CompositionLocalProvider(LocalCustomColorTheme provides colorsCustom) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}
