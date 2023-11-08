package com.pokemon.feature.setting.navigation

sealed class SettingNavigationItem(val route: String) {
    data object Setting : SettingNavigationItem(route = "setting")
}