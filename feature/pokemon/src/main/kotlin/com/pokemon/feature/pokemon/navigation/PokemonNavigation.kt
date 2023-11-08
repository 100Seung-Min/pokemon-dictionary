package com.pokemon.feature.pokemon.navigation

import androidx.navigation.NavController

fun NavController.navigatePokemonDetail(pokemonId: Int) {
    navigate(PokemonNavigationItem.Detail.route + PokemonDeepLinkKey.ID + pokemonId)
}