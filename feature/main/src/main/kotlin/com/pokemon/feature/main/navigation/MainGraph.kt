package com.pokemon.feature.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.navigation.home.MainNavigationItem
import com.pokemon.feature.main.home.HomeScreen
import com.pokemon.feature.main.splash.SplashScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainGraph(navController: NavController, changeDarkTheme: (Boolean) -> Unit) {
    composable(route = MainNavigationItem.Home.route) {
        HomeScreen(navController = navController)
    }

    composable(route = MainNavigationItem.Splash.route) {
        SplashScreen(navController = navController, changeDarkTheme = changeDarkTheme)
    }
}