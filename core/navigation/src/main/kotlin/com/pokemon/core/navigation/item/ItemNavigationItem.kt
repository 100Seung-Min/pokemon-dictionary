package com.pokemon.core.navigation.item

sealed class ItemNavigationItem(val route: String) {
    data object Item: ItemNavigationItem(route = "item")
}
