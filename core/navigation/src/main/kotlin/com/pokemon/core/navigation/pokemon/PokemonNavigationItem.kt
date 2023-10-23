package com.pokemon.core.navigation.pokemon

sealed class PokemonNavigationItem(val route: String) {
    data object Detail: PokemonNavigationItem(route = "detail")
}