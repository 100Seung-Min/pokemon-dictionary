package com.pokemon.feature.academy.navigation

import androidx.navigation.NavController

fun NavController.navigateQuiz(route: String, quizId: Int) {
    if (quizId < 19) navigate(route + AcademyDeepLinkKey.QuizId + quizId) {
        popUpTo(AcademyNavigationItem.Academy.route)
    } else navigate(AcademyNavigationItem.Result.route) {
        popUpTo(AcademyNavigationItem.Academy.route)
    }
}