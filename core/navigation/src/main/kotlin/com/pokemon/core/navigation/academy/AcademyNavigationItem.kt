package com.pokemon.core.navigation.academy

sealed class AcademyNavigationItem(val route: String) {
    data object Academy: AcademyNavigationItem(route = "academy")

    data object Easy: AcademyNavigationItem(route = "easy")

    data object Normal: AcademyNavigationItem(route = "normal")

    data object Hard: AcademyNavigationItem(route = "hard")
}