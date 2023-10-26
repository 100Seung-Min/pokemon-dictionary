package com.pokemon.feature.academy.academy

import androidx.lifecycle.ViewModel
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
) : ContainerHost<AcademyState, Unit>, ViewModel() {
    override val container = container<AcademyState, Unit>(AcademyState())

    fun setQuizList() = intent {
        reduce {
            state.copy(
                quizList = (1..1017).toList().shuffled().take(20),
                answerList = listOf()
            )
        }
    }

    fun getEasyQuiz(index: Int, finishLoading: () -> Unit) = intent {
        val quizNumber = state.quizList[index]
        val quizList = mutableListOf<QuizModel>()
        reduce { state.copy(easyQuiz = List(5) { QuizModel(id = 0, name = "") }) }
        kotlin.runCatching {
            for (i in 0..4) {
                val plusIndex = if (quizNumber < 5) i else i * -1
                getPokemonDetailUseCase(quizNumber + plusIndex).onSuccess {
                    quizList.add(QuizModel(id = it.id, name = it.name))
                }
            }
        }.onSuccess {
            reduce { state.copy(easyQuiz = quizList.shuffled()) }
            finishLoading()
        }
    }

    fun addAnswer(isAnswer: Boolean) = intent {
        reduce { state.copy(answerList = state.answerList.plus(isAnswer)) }
    }
}