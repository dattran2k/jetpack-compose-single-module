package com.dat.base_compose.presenstation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.dat.base_compose.data.model.local.DarkThemeConfig
import com.dat.base_compose.presenstation.MainActivityUiState

@Composable
fun shouldUseDarkTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    MainActivityUiState.Loading -> isSystemInDarkTheme()
    is MainActivityUiState.Success -> when (uiState.userData.darkThemeConfig) {
        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
    }
}

data class MyColorPalette(
    val backGround: Color,
    val textTitle: Color,
    val todoItemBackGround: Color
)



val LightTheme = MyColorPalette(
    backGround = WhiteColor,
    textTitle = BlackColor,
    todoItemBackGround = LineColor
)
val DarkTheme = MyColorPalette(
    backGround = BlackColor,
    textTitle = WhiteColor,
    todoItemBackGround = WhiteColorAlpha10
)

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
fun BaseJetpackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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
