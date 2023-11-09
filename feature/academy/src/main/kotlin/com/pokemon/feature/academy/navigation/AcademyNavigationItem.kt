package com.pokemon.feature.academy.navigation

sealed class AcademyNavigationItem(val route: String) {
    data object Academy : AcademyNavigationItem(route = "academy")

    data object PokemonNameQuiz : AcademyNavigationItem(route = "pokemonNameQuiz")

    data object GenerationToPokemonQuiz : AcademyNavigationItem(route = "generationToPokemonQuiz")

    data object PokemonToGenerationQuiz : AcademyNavigationItem(route = "pokemonToGenerationQuiz")

    data object PokemonSoundQuiz : AcademyNavigationItem(route = "pokemonSoundQuiz")

    data object Result : AcademyNavigationItem(route = "result")
}