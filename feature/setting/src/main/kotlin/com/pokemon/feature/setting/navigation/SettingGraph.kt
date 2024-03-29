package com.pokemon.feature.setting.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.feature.setting.setting.SettingScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingGraph(changeDarkTheme: (Boolean) -> Unit) {
    composable(route = SettingNavigationItem.Setting.route) {
        SettingScreen(changeDarkTheme = changeDarkTheme)
    }
}