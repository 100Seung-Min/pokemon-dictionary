package com.pokemon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.navigation.home.MainNavigationItem
import com.pokemon.feature.main.navigation.homeGraph
import com.pokemon.feature.pokemon.navigation.pokemonGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            PokemonTheme {
                BaseApp(navController = navController)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseApp(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = MainNavigationItem.Main.route,
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
        homeGraph(navController = navController)
        pokemonGraph(navController = navController)
    }
}