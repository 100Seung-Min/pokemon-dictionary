package com.pokemon.feature.academy.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.navigation.academy.AcademyNavigationItem
import com.pokemon.feature.academy.academy.AcademyScreen
import com.pokemon.feature.academy.easy.EasyScreen
import com.pokemon.feature.academy.hard.HardScreen
import com.pokemon.feature.academy.normal.NormalScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.academyGraph(navController: NavController) {
    composable(route = AcademyNavigationItem.Academy.route) {
        AcademyScreen(navController = navController)
    }

    composable(route = AcademyNavigationItem.Easy.route) {
        EasyScreen()
    }

    composable(route = AcademyNavigationItem.Normal.route) {
        NormalScreen()
    }

    composable(route = AcademyNavigationItem.Hard.route) {
        HardScreen()
    }
}