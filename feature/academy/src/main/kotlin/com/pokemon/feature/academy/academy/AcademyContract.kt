package com.pokemon.feature.academy.academy

import com.pokemon.core.ui.model.QuizModel

data class AcademyState(
    val pokemonQuizList: List<Int> = (1..1017).toList(),
    val answerId: Int = 0,
    val quizList: List<QuizModel> = List(5) { QuizModel(id = 0, name = "") },
    val answerList: List<Boolean> = listOf(),
)