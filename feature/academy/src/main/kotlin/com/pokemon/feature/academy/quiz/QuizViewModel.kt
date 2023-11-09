package com.pokemon.feature.academy.quiz

import androidx.lifecycle.ViewModel
import com.pokemon.core.domain.usecase.generation.GetGenerationDetailUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonDetailUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.pokemon.core.domain.usecase.type.GetTypeDetailUseCase
import com.pokemon.core.ui.model.QuizModel
import com.pokemon.core.ui.util.typeList
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
    private val getTypeDetailUseCase: GetTypeDetailUseCase,
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
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
                        reduce {
                            state.copy(
                                pokemonId = it.id,
                                soundUrl = "https://play.pokemonshowdown.com/audio/cries/${it.englishName}.ogg"
                            )
                        }
                    }
                }
            }
        }.onSuccess {
            reduce { state.copy(quizList = quizList.shuffled()) }
            finishLoading()
        }
    }

    fun generationQuiz(isToPokemon: Boolean, finishLoading: () -> Unit) = intent {
        val answerGenerationId = (1..9).random()
        val generationList =
            (1..9).filter { it != answerGenerationId }.shuffled().take(4).plus(answerGenerationId)
        val quizList = mutableListOf<QuizModel>()
        reduce {
            state.copy(
                quizList = List(5) { QuizModel(id = 0, name = "") },
                generationId = answerGenerationId
            )
        }
        kotlin.runCatching {
            for (generationId in generationList) {
                getGenerationDetailUseCase(generationId = generationId).onSuccess {
                    val quizId = it.pokemonList.random().id
                    if (generationId == answerGenerationId) {
                        reduce { state.copy(pokemonId = quizId) }
                    }
                    if (isToPokemon) {
                        getPokemonDetailUseCase(pokemonId = quizId).onSuccess {
                            quizList.add(QuizModel(id = quizId, name = it.name))
                        }
                    } else {
                        quizList.add(QuizModel(id = generationId, name = it.name))
                    }
                }
            }
        }.onSuccess {
            reduce { state.copy(quizList = quizList.shuffled()) }
            finishLoading()
        }
    }

    fun typeQuiz(finishLoading: () -> Unit) = intent {
        val answerTypeId = (1..18).random()
        val quizList = mutableListOf<QuizModel>()
        getTypeDetailUseCase(typeId = answerTypeId).onSuccess {
            val answerPokemon = it.pokemonList.random()
            reduce { state.copy(typeId = it.id, pokemonId = answerPokemon.id) }
            quizList.add(QuizModel(id = it.id, name = it.name))
            getPokemonInfoUseCase(answerPokemon.id).onSuccess { infoPokemonEntity ->
                typeList.filter { !infoPokemonEntity.typeList.contains(it) }.shuffled().take(4)
                    .forEach {
                        quizList.add(QuizModel(id = 0, name = it))
                    }
                reduce { state.copy(quizList = quizList.shuffled()) }
                finishLoading()
            }
        }
    }
}