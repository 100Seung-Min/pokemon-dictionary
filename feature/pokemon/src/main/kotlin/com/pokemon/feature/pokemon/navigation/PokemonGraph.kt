package com.pokemon.feature.pokemon.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pokemon.core.design_system.component.animateComposable
import com.pokemon.core.navigation.pokemon.PokemonDeepLinkKey
import com.pokemon.core.navigation.pokemon.PokemonNavigationItem
import com.pokemon.feature.pokemon.detail.DetailScreen

fun NavGraphBuilder.pokemonGraph(navController: NavController) {
    animateComposable(
        route = PokemonNavigationItem.Detail.route +
                PokemonDeepLinkKey.ID + "{${PokemonDeepLinkKey.ID}}",
        arguments = listOf(
            navArgument(PokemonDeepLinkKey.ID) {
                type = NavType.IntType
            }
        )
    ) {
        val id = it.arguments?.getInt(PokemonDeepLinkKey.ID) ?: 1
        DetailScreen(navController = navController, id = id)
    }
}