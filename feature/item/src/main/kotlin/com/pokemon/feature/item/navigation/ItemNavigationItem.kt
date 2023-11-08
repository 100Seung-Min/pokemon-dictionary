package com.pokemon.feature.item.navigation

sealed class ItemNavigationItem(val route: String) {
    data object Item: ItemNavigationItem(route = "item")
}
