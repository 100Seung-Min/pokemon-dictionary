package com.pokemon.feature.academy.academy

import com.pokemon.core.ui.model.QuizModel

data class AcademyState(
    val quizList: List<Int> = (1..1017).toList().shuffled().take(20),
    val easyQuiz: List<QuizModel> = List(5) { QuizModel(id = 0, name = "") },
    val answerList: List<Boolean> = listOf(),
)