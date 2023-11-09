package com.pokemon.feature.academy.academy

import com.pokemon.feature.academy.navigation.QuizLevel

data class AcademyState(
    val answerList: List<Boolean> = listOf(),
    val quizLevel: QuizLevel = QuizLevel.Easy,
)