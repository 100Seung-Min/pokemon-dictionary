package com.pokemon.feature.pokemon.navigation

sealed class PokemonNavigationItem(val route: String) {
    data object Detail: PokemonNavigationItem(route = "detail")
}