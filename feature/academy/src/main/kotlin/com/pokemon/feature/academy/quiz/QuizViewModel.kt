package com.pokemon.feature.academy.quiz

import androidx.lifecycle.ViewModel
import com.pokemon.core.domain.usecase.generation.GetGenerationDetailUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonDetailUseCase
import com.pokemon.core.ui.model.QuizModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getGenerationDetailUseCase: GetGenerationDetailUseCase,
) : ContainerHost<QuizState, Unit>, ViewModel() {
    override val container = container<QuizState, Unit>(QuizState())

    fun pokemonNameQuiz(finishLoading: () -> Unit) = intent {
        val answerPokemonId = (1..1017).random()
        val quizList = mutableListOf<QuizModel>()
        reduce { state.copy(quizList = List(5) { QuizModel(id = 0, name = "") }) }
        kotlin.runCatching {
            for (i in 0..4) {
                val plusIndex = if (answerPokemonId < 500) i * 5 else i * -5
                getPokemonDetailUseCase(answerPokemonId + plusIndex).onSuccess {
                    quizList.add(QuizModel(id = it.id, name = it.name))
                    if (i == 0) {
                        reduce { state.copy(answerId = it.id) }
                    }
                }
            }
        }.onSuccess {
            reduce { state.copy(quizList = quizList.shuffled()) }
            finishLoading()
        }
    }

    fun generationToPokemonQuiz(finishLoading: () -> Unit) = intent {
        val answerGenerationId = (1..9).random()
        val quizList = mutableListOf<QuizModel>()
        reduce {
            state.copy(
                quizList = List(5) { QuizModel(id = 0, name = "") },
                generationId = answerGenerationId
            )
        }
        kotlin.runCatching {
            for (i in 0..4) {
                val plusIndex = if (answerGenerationId < 5) i else -i
                getGenerationDetailUseCase(generationId = answerGenerationId + plusIndex).onSuccess {
                    val quizId = it.pokemonList.random().id
                    getPokemonDetailUseCase(pokemonId = quizId).onSuccess {
                        quizList.add(QuizModel(id = quizId, name = it.name))
                    }
                    if (i == 0) {
                        reduce { state.copy(answerId = quizId) }
                    }
                }
            }
        }.onSuccess {
            reduce { state.copy(quizList = quizList.shuffled()) }
            finishLoading()
        }
    }
}