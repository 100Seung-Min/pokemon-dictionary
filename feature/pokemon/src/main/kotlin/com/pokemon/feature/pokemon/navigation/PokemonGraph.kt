package com.pokemon.feature.pokemon.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.navigation.pokemon.PokemonDeepLinkKey
import com.pokemon.core.navigation.pokemon.PokemonNavigationItem
import com.pokemon.feature.pokemon.detail.DetailScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.pokemonGraph(navController: NavController) {
    composable(
        route = PokemonNavigationItem.Detail.route +
                PokemonDeepLinkKey.ID + "{${PokemonDeepLinkKey.ID}}",
        arguments = listOf(
            navArgument(PokemonDeepLinkKey.ID) {
                type = NavType.IntType
            }
        ),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500
                )
            )
        },
        popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 500)) },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it * 2 }, animationSpec = tween(
                    durationMillis = 500
                )
            )
        }
    ) {
        val id = it.arguments?.getInt(PokemonDeepLinkKey.ID) ?: 1
        DetailScreen(navController = navController, id = id)
    }
}