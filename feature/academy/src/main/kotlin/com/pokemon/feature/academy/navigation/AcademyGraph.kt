package com.pokemon.feature.academy.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.design_system.component.animateComposable
import com.pokemon.feature.academy.academy.AcademyScreen
import com.pokemon.feature.academy.quiz.screen.PokemonNameQuizScreen
import com.pokemon.feature.academy.quiz.screen.PokemonToGenerationQuizScreen
import com.pokemon.feature.academy.quiz.screen.GenerationToPokemonQuizScreen
import com.pokemon.feature.academy.quiz.screen.PokemonSoundQuizScreen
import com.pokemon.feature.academy.result.ResultScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.academyGraph(navigateQuiz: (QuizLevel, Int) -> Unit) {
    composable(route = AcademyNavigationItem.Academy.route) {
        AcademyScreen(navigateQuiz = navigateQuiz)
    }

    animateComposable(
        route = AcademyNavigationItem.PokemonNameQuiz.route
                + AcademyDeepLinkKey.QuizId + "{${AcademyDeepLinkKey.QuizId}}",
        arguments = listOf(
            navArgument(AcademyDeepLinkKey.QuizId) {
                type = NavType.IntType
            }
        )
    ) {
        val quizId = it.arguments?.getInt(AcademyDeepLinkKey.QuizId) ?: 0
        PokemonNameQuizScreen(navigateQuiz = navigateQuiz, quizId = quizId)
    }

    animateComposable(
        route = AcademyNavigationItem.GenerationToPokemonQuiz.route
                + AcademyDeepLinkKey.QuizId + "{${AcademyDeepLinkKey.QuizId}}",
        arguments = listOf(
            navArgument(AcademyDeepLinkKey.QuizId) {
                type = NavType.IntType
            }
        )
    ) {
        val quizId = it.arguments?.getInt(AcademyDeepLinkKey.QuizId) ?: 0
        GenerationToPokemonQuizScreen(navigateQuiz = navigateQuiz, quizId = quizId)
    }

    animateComposable(
        route = AcademyNavigationItem.PokemonToGenerationQuiz.route
                + AcademyDeepLinkKey.QuizId + "{${AcademyDeepLinkKey.QuizId}}",
        arguments = listOf(
            navArgument(AcademyDeepLinkKey.QuizId) {
                type = NavType.IntType
            }
        )
    ) {
        val quizId = it.arguments?.getInt(AcademyDeepLinkKey.QuizId) ?: 0
        PokemonToGenerationQuizScreen(navigateQuiz = navigateQuiz, quizId = quizId)
    }

    animateComposable(
        route = AcademyNavigationItem.PokemonSoundQuiz.route
                + AcademyDeepLinkKey.QuizId + "{${AcademyDeepLinkKey.QuizId}}",
        arguments = listOf(
            navArgument(AcademyDeepLinkKey.QuizId) {
                type = NavType.IntType
            }
        )
    ) {
        val quizId = it.arguments?.getInt(AcademyDeepLinkKey.QuizId) ?: 0
        PokemonSoundQuizScreen(quizId = quizId)
    }

    animateComposable(
        route = AcademyNavigationItem.Result.route,
    ) {
        ResultScreen()
    }
}