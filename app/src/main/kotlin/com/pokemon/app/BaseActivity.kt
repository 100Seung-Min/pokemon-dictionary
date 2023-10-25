package com.pokemon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.navigation.home.MainNavigationItem
import com.pokemon.feature.academy.navigation.academyGraph
import com.pokemon.feature.main.navigation.PokemonBottomNavigation
import com.pokemon.feature.main.navigation.homeGraph
import com.pokemon.feature.pokemon.navigation.pokemonGraph
import com.pokemon.feature.setting.navigation.settingGraph
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
                Scaffold(
                    bottomBar = { PokemonBottomNavigation(navController = navController) },
                    content = {
                        BaseApp(
                            modifier = Modifier.padding(it),
                            navController = navController
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseApp(modifier: Modifier = Modifier, navController: NavHostController) {
    AnimatedNavHost(
        modifier = modifier,
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
        academyGraph(navController = navController)
        settingGraph(navController = navController)
        pokemonGraph(navController = navController)
    }
}