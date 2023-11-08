package com.pokemon.app.bottom_navigation

import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.feature.academy.navigation.AcademyNavigationItem
import com.pokemon.feature.item.navigation.ItemNavigationItem
import com.pokemon.feature.main.navigation.MainNavigationItem
import com.pokemon.feature.setting.navigation.SettingNavigationItem

enum class BottomNavigationItem(val route: String, val icon: PokemonIconList) {
    Home(route = MainNavigationItem.Home.route, icon = PokemonIconList.NavHome),
    Item(route = ItemNavigationItem.Item.route, icon = PokemonIconList.NavItem),
    Academy(route = AcademyNavigationItem.Academy.route, icon = PokemonIconList.NavAcademy),
    Setting(route = SettingNavigationItem.Setting.route, icon = PokemonIconList.NavSetting)
}