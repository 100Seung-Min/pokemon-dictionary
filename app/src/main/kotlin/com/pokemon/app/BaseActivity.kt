package com.pokemon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.navigation.home.MainNavigationItem
import com.pokemon.feature.academy.navigation.academyGraph
import com.pokemon.feature.item.navigation.itemGraph
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
            var isDarkTheme by remember { mutableStateOf(false) }

            PokemonTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    backgroundColor = PokemonTheme.colors.main,
                    bottomBar = { PokemonBottomNavigation(navController = navController) },
                    content = {
                        BaseApp(
                            modifier = Modifier.padding(it),
                            navController = navController,
                            changeDarkTheme = { isDarkTheme = it }
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BaseApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    changeDarkTheme: (Boolean) -> Unit,
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainNavigationItem.Main.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        homeGraph(navController = navController, changeDarkTheme = changeDarkTheme)
        itemGraph(navController = navController)
        academyGraph(navController = navController)
        settingGraph(navController = navController, changeDarkTheme = changeDarkTheme)
        pokemonGraph(navController = navController)
    }
}