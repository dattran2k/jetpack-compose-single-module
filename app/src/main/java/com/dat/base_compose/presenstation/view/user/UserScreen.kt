package com.dat.base_compose.presenstation.view.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.base_compose.R
import com.dat.base_compose.data.model.local.DarkThemeConfig
import com.dat.base_compose.presenstation.theme.BaseJetpackComposeTheme
import com.dat.base_compose.presenstation.theme.CustomColorTheme
import com.dat.base_compose.presenstation.theme.PrimaryColor


@Composable
fun UserScreenRoute(viewModel: UserViewModel = hiltViewModel()) {
    val darkModeState by viewModel.userUIState.collectAsStateWithLifecycle()
    UserScreen(darkModeState, viewModel::updateDarkMode)
}

@Composable
fun UserScreen(
    userUIState: UserUIState,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
    Column(
        Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .background(
                CustomColorTheme.current.backGround
            )
    ) {
        when (userUIState) {
            UserUIState.Loading -> {
                Text(
                    text = stringResource(R.string.loading),
                    modifier = Modifier.padding(vertical = 16.dp),
                )
            }

            is UserUIState.Success -> {
                SettingsPanel(darkMode = userUIState.darkMode, onChangeDarkThemeConfig)
            }
        }
    }
}

@Composable
fun SettingsPanel(
    darkMode: DarkThemeConfig,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = DarkThemeConfig.FOLLOW_SYSTEM.configName,
            selected = darkMode == DarkThemeConfig.FOLLOW_SYSTEM,
            onClick = { onChangeDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM) },
        )
        SettingsDialogThemeChooserRow(
            text = DarkThemeConfig.LIGHT.configName,
            selected = darkMode == DarkThemeConfig.LIGHT,
            onClick = { onChangeDarkThemeConfig(DarkThemeConfig.LIGHT) },
        )
        SettingsDialogThemeChooserRow(
            text = DarkThemeConfig.DARK.configName,
            selected = darkMode == DarkThemeConfig.DARK,
            onClick = { onChangeDarkThemeConfig(DarkThemeConfig.DARK) },
        )
    }
}

@Composable
fun SettingsDialogThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = PrimaryColor,
                unselectedColor =  CustomColorTheme.current.textTitle
            ),
        )
        Spacer(Modifier.width(8.dp))
        Text(text, color = CustomColorTheme.current.textTitle)
    }
}

@Preview
@Composable
private fun PreviewUser() {
    BaseJetpackComposeTheme {
        UserScreen(userUIState = UserUIState.Success(
            DarkThemeConfig.LIGHT
        ), onChangeDarkThemeConfig = {

        })
    }
}