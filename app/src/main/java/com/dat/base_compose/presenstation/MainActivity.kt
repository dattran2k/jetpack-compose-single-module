package com.dat.base_compose.presenstation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dat.base_compose.presenstation.navigation.Screen
import com.dat.base_compose.presenstation.theme.BaseJetpackComposeTheme
import com.dat.base_compose.presenstation.theme.ThemeState
import com.dat.base_compose.presenstation.view.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            BaseJetpackComposeTheme(ThemeState.isDark) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, Screen.Main.route) {
                        composable(Screen.Main.route) {
                            MainScreen(navController)
                        }
                    }
                }
            }

        }
    }
}
