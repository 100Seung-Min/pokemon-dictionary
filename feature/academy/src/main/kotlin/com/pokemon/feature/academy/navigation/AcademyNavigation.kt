package com.pokemon.feature.academy.navigation

import androidx.navigation.NavController

enum class QuizLevel(val routeList: List<AcademyNavigationItem>) {
    Easy(listOf(AcademyNavigationItem.PokemonNameQuiz)),
    Normal(
        Easy.routeList.plus(
            listOf(
                AcademyNavigationItem.GenerationToPokemonQuiz,
                AcademyNavigationItem.PokemonToGenerationQuiz
            )
        )
    ),
    Hard(
        Normal.routeList.plus(
            listOf(
                AcademyNavigationItem.PokemonSoundQuiz,
                AcademyNavigationItem.PokemonTypeQuiz
            )
        )
    )
}

fun NavController.navigateQuiz(quizLevel: QuizLevel, quizId: Int) {
    if (quizId <= 19) navigate(quizLevel.routeList.random().route + AcademyDeepLinkKey.QuizId + quizId) {
        popUpTo(AcademyNavigationItem.Academy.route)
    } else navigate(AcademyNavigationItem.Result.route) {
        popUpTo(AcademyNavigationItem.Academy.route)
    }
}