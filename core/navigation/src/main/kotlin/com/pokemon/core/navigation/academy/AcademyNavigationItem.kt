package com.pokemon.core.navigation.academy

sealed class AcademyNavigationItem(val route: String) {
    data object Academy: AcademyNavigationItem(route = "academy")
}