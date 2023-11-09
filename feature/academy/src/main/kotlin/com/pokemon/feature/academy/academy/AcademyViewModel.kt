package com.pokemon.feature.academy.academy

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
class AcademyViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getGenerationDetailUseCase: GetGenerationDetailUseCase,
) : ContainerHost<AcademyState, Unit>, ViewModel() {
    override val container = container<AcademyState, Unit>(AcademyState())

    fun setQuizList() = intent {
        reduce {
            state.copy(answerList = listOf())
        }
    }

    fun pokemonNameQuiz(finishLoading: () -> Unit) = intent {
        val quizNumber = state.pokemonQuizList.random()
        val quizList = mutableListOf<QuizModel>()
        reduce { state.copy(quizList = List(5) { QuizModel(id = 0, name = "") }) }
        kotlin.runCatching {
            for (i in 0..4) {
                val plusIndex = if (quizNumber < 500) i * 5 else i * -5
                getPokemonDetailUseCase(quizNumber + plusIndex).onSuccess {
                    quizList.add(QuizModel(id = it.id, name = it.name))
                    println("안녕 ${it.id}, ${it.name}")
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
        reduce { state.copy(quizList = List(5) { QuizModel(id = 0, name = "") }) }
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

    fun addAnswer(isAnswer: Boolean) = intent {
        reduce { state.copy(answerList = state.answerList.plus(isAnswer)) }
    }
}