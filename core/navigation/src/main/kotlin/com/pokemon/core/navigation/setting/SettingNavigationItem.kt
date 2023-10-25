package com.pokemon.core.navigation.setting

sealed class SettingNavigationItem(val route: String) {
    data object Setting : SettingNavigationItem(route = "setting")
}