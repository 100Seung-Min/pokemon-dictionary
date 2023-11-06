package com.pokemon.feature.item.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.navigation.item.ItemNavigationItem
import com.pokemon.feature.item.item.ItemScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.itemGraph(navController: NavController) {
    composable(route = ItemNavigationItem.Item.route) {
        ItemScreen()
    }
}