package com.pokemon.feature.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.feature.main.home.HomeScreen
import com.pokemon.feature.main.splash.SplashScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(
    navigatePokemonDetail: (Int) -> Unit,
    navigateHome: () -> Unit,
    changeDarkTheme: (Boolean) -> Unit,
) {
    composable(route = MainNavigationItem.Home.route) {
        HomeScreen(navigatePokemonDetail = navigatePokemonDetail)
    }

    composable(route = MainNavigationItem.Splash.route) {
        SplashScreen(navigateHome = navigateHome, changeDarkTheme = changeDarkTheme)
    }
}