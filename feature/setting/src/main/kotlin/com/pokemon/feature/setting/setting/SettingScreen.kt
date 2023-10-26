package com.pokemon.feature.setting.setting

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.component.RemoveOverScrollLazyColumn
import com.pokemon.core.design_system.util.Language
import com.pokemon.core.ui.component.LanguageItem
import com.pokemon.core.design_system.R

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel(),
) {
    PokemonBackground {
        val languageList = Language.values().toList()
        RemoveOverScrollLazyColumn {
            item {
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