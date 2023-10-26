package com.pokemon.feature.main.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pokemon.core.design_system.attribute.PokemonIcon
import com.pokemon.core.navigation.bottom_navigation.BottomNavigationItem

@Composable
fun PokemonBottomNavigation(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val itemList = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Academy,
        BottomNavigationItem.Setting
    )
    AnimatedVisibility(
        visible = itemList.map { it.route }.contains(currentRoute),
        enter = slideInVertically(
            initialOffsetY = { 0 },
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutVertically(
            targetOffsetY = { 0 },
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        BottomNavigation(
            modifier = Modifier.height(45.dp),
            backgroundColor = Color.LightGray,
            contentColor = Color.Black
        ) {
            itemList.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        PokemonIcon(
                            icon = item.icon,
                            alpha = if (currentRoute == item.route) 1F else 0.4F
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Gray,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}