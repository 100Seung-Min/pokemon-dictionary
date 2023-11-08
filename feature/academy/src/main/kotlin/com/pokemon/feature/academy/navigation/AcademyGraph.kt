package com.pokemon.feature.academy.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.pokemon.core.design_system.component.animateComposable
import com.pokemon.feature.academy.academy.AcademyScreen
import com.pokemon.feature.academy.easy.EasyScreen
import com.pokemon.feature.academy.hard.HardScreen
import com.pokemon.feature.academy.normal.NormalScreen
import com.pokemon.feature.academy.result.ResultScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.academyGraph(navigateQuiz: (String, Int) -> Unit) {
    composable(route = AcademyNavigationItem.Academy.route) {
        AcademyScreen(navigateQuiz = navigateQuiz)
    }

    animateComposable(
        route = AcademyNavigationItem.Easy.route
                + AcademyDeepLinkKey.QuizId + "{${AcademyDeepLinkKey.QuizId}}",
        arguments = listOf(
            navArgument(AcademyDeepLinkKey.QuizId) {
                type = NavType.IntType
            }
        )
    ) {
        val quizId = it.arguments?.getInt(AcademyDeepLinkKey.QuizId) ?: 0
        EasyScreen(navigateQuiz = navigateQuiz, quizId = quizId)
    }

    animateComposable(
        route = AcademyNavigationItem.Normal.route
                + AcademyDeepLinkKey.QuizId + "{${AcademyDeepLinkKey.QuizId}}",
        arguments = listOf(
            navArgument(AcademyDeepLinkKey.QuizId) {
                type = NavType.IntType
            }
        )
    ) {
        val quizId = it.arguments?.getInt(AcademyDeepLinkKey.QuizId) ?: 0
        NormalScreen(quizId = quizId)
    }

    animateComposable(
        route = AcademyNavigationItem.Hard.route
                + AcademyDeepLinkKey.QuizId + "{${AcademyDeepLinkKey.QuizId}}",
        arguments = listOf(
            navArgument(AcademyDeepLinkKey.QuizId) {
                type = NavType.IntType
            }
        )
    ) {
        val quizId = it.arguments?.getInt(AcademyDeepLinkKey.QuizId) ?: 0
        HardScreen(quizId = quizId)
    }

    animateComposable(
        route = AcademyNavigationItem.Result.route,
    ) {
        ResultScreen()
    }
}