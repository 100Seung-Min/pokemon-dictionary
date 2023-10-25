package com.pokemon.core.navigation.bottom_navigation

import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.navigation.academy.AcademyNavigationItem
import com.pokemon.core.navigation.home.MainNavigationItem
import com.pokemon.core.navigation.setting.SettingNavigationItem

sealed class BottomNavigationItem(val route: String, val icon: PokemonIconList) {
    data object Home : BottomNavigationItem(
        route = MainNavigationItem.Main.route,
        icon = PokemonIconList.NavHome
    )

    data object Academy : BottomNavigationItem(
        route = AcademyNavigationItem.Academy.route,
        icon = PokemonIconList.NavAcademy
    )

    data object Setting : BottomNavigationItem(
        route = SettingNavigationItem.Setting.route,
        icon = PokemonIconList.NavSetting
    )
}