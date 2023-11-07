package com.pokemon.core.navigation.home

sealed class MainNavigationItem(val route: String) {
    data object Home : MainNavigationItem(route = "home")

    data object Splash : MainNavigationItem(route = "splash")
}