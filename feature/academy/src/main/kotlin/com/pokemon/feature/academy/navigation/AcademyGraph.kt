package com.pokemon.feature.academy.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.navigation.academy.AcademyNavigationItem
import com.pokemon.feature.academy.academy.AcademyScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.academyGraph(navController: NavController) {
    composable(route = AcademyNavigationItem.Academy.route) {
        AcademyScreen()
    }
}