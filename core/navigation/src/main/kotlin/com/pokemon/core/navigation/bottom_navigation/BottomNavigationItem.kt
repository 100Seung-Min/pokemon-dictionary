package com.pokemon.core.navigation.bottom_navigation

import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.navigation.academy.AcademyNavigationItem
import com.pokemon.core.navigation.home.MainNavigationItem
import com.pokemon.core.navigation.item.ItemNavigationItem
import com.pokemon.core.navigation.setting.SettingNavigationItem

enum class BottomNavigationItem(val route: String, val icon: PokemonIconList) {
    Home(route = MainNavigationItem.Main.route, icon = PokemonIconList.NavHome),
    Item(route = ItemNavigationItem.Item.route, icon = PokemonIconList.NavItem),
    Academy(route = AcademyNavigationItem.Academy.route, icon = PokemonIconList.NavAcademy),
    Setting(route = SettingNavigationItem.Setting.route, icon = PokemonIconList.NavSetting)
}