package com.pokemon.feature.academy.academy

import androidx.lifecycle.ViewModel
import com.pokemon.feature.academy.navigation.QuizLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AcademyViewModel @Inject constructor(
) : ContainerHost<AcademyState, Unit>, ViewModel() {
    override val container = container<AcademyState, Unit>(AcademyState())

    fun initQuiz(quizLevel: QuizLevel) = intent {
        reduce { state.copy(answerList = listOf(), quizLevel = quizLevel) }
    }

    fun addAnswer(isAnswer: Boolean) = intent {
        reduce { state.copy(answerList = state.answerList.plus(isAnswer)) }
    }
}