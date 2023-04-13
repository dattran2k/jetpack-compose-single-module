package com.dat.base_compose.presenstation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dat.base_compose.presenstation.theme.BaseJetpackComposeTheme
import com.dat.base_compose.presenstation.theme.LocalCustomColorTheme
import com.dat.base_compose.presenstation.theme.ThemeState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseJetpackComposeTheme(ThemeState.isDark) {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(Modifier
                            .fillMaxSize()
                            .background(LocalCustomColorTheme.current.HomeBackGround),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = "Test", color = LocalCustomColorTheme.current.homeTextTitle)
                        Button(onClick = { ThemeState.isDark = !ThemeState.isDark }) {
                            Text(text = "Click me to update theme")
                        }
                    }
                }
            }
        }
    }
}
