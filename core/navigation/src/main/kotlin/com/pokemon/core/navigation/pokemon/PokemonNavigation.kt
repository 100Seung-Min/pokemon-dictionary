package com.pokemon.core.navigation.pokemon

import androidx.navigation.NavController

fun NavController.navigatePokemonDetail(pokemonId: Int) {
    navigate(PokemonNavigationItem.Detail.route + PokemonDeepLinkKey.ID + pokemonId)
}