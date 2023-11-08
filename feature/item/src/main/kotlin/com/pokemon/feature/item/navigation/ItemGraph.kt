package com.pokemon.feature.item.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.pokemon.feature.item.item.ItemScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.itemGraph() {
    composable(route = ItemNavigationItem.Item.route) {
        ItemScreen()
    }
}