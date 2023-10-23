package com.pokemon.core.navigation.home

sealed class MainNavigationItem(val route: String) {
    data object Main : MainNavigationItem(route = "home")
}