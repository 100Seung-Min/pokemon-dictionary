package com.pokemon.feature.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.navigation.home.MainNavigationItem
import com.pokemon.feature.main.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable(route = MainNavigationItem.Main.route) {
        HomeScreen(navController = navController)
    }
}