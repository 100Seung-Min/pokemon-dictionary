package com.pokemon.feature.academy.quiz

import com.pokemon.core.ui.model.QuizModel

data class QuizState(
    val quizList: List<QuizModel> = List(5) { QuizModel(id = 0, name = "") },
    val soundUrl: String = "",
    val generationId: Int = 0,
    val pokemonId: Int = 0,
    val typeId: Int = 0,
)