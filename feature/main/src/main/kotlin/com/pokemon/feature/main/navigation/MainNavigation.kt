package com.pokemon.feature.main.navigation

import androidx.navigation.NavController

fun NavController.navigateHome() = navigate(MainNavigationItem.Home.route) {
    popUpTo(MainNavigationItem.Splash.route) {
        inclusive = true
    }
}