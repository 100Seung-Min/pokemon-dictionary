package com.pokemon.feature.setting.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.component.RemoveOverScrollLazyColumn
import com.pokemon.core.design_system.util.Language
import com.pokemon.core.design_system.R
import com.pokemon.core.ui.component.item.LanguageItem

@Composable
fun SettingScreen(
    changeDarkTheme: (Boolean) -> Unit,
    settingViewModel: SettingViewModel = hiltViewModel(),
) {
    val container = settingViewModel.container
    val state = container.stateFlow.collectAsState().value

    LaunchedEffect(state.isDarkTheme) {
        if (state.isDarkTheme != null) {
            changeDarkTheme(state.isDarkTheme)
        }
    }

    PokemonBackground {
        val languageList = Language.values().toList()
        RemoveOverScrollLazyColumn {
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PokemonText(text = stringResource(id = R.string.dark_theme))
                    Switch(
                        checked = state.isDarkTheme ?: false,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = PokemonTheme.colors.main,
                            uncheckedThumbColor = PokemonTheme.colors.main,
                            checkedTrackColor = PokemonTheme.colors.text,
                            uncheckedTrackColor = PokemonTheme.colors.text,
                        ),
                        onCheckedChange = {
                            settingViewModel.saveIsDarkTheme(it)
                        },
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                PokemonText(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = stringResource(id = R.string.setting_language)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            itemsIndexed(languageList) { index, item ->
                LanguageItem(item = item, index = index, isLast = index == languageList.lastIndex) {
                    settingViewModel.saveLanguage(item.type)
                }
            }
        }
    }
}