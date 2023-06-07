package com.dat.base_compose.presenstation.view.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dat.base_compose.presenstation.theme.LocalCustomColorTheme
import com.dat.base_compose.presenstation.theme.PrimaryColor
import com.dat.base_compose.presenstation.theme.ThemeState

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(LocalCustomColorTheme.current.HomeBackGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Test", color = LocalCustomColorTheme.current.homeTextTitle)

        Text(text = "Click me to update theme", modifier = Modifier
            .clickable {
                ThemeState.isDark = !ThemeState.isDark
            }
            .background(PrimaryColor, shape = RoundedCornerShape(30))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}