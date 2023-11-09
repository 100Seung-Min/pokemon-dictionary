package com.pokemon.feature.academy.quiz

import com.pokemon.core.ui.model.QuizModel

data class QuizState(
    val quizList: List<QuizModel> = List(5) { QuizModel(id = 0, name = "") },
    val generationId: Int = 0,
    val answerId: Int = 0,
)