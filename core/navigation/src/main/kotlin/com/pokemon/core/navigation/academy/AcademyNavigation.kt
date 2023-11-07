package com.pokemon.core.navigation.academy

import androidx.navigation.NavController

fun NavController.navigateQuizScreen(route: String, quizId: Int) =
    navigate(route + AcademyDeepLinkKey.QuizId + quizId)