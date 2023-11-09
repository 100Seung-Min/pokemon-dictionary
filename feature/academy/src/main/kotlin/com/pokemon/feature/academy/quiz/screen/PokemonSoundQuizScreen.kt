package com.pokemon.feature.academy.quiz.screen

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pokemon.core.design_system.attribute.PokemonIcon
import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.component.RemoveOverScrollLazyColumn
import com.pokemon.core.ui.component.item.QuizNameItem
import com.pokemon.core.ui.util.getActivity
import com.pokemon.core.ui.util.pokemonClickable
import com.pokemon.feature.academy.academy.AcademyViewModel
import com.pokemon.feature.academy.navigation.QuizLevel
import com.pokemon.feature.academy.quiz.QuizViewModel

@Composable
fun PokemonSoundQuizScreen(
    navigateQuiz: (QuizLevel, Int) -> Unit,
    quizId: Int,
    academyViewModel: AcademyViewModel = hiltViewModel(getActivity()),
    quizViewModel: QuizViewModel = hiltViewModel(),
) {
    val quizContainer = quizViewModel.container
    val quizState = quizContainer.stateFlow.collectAsState().value
    val academyContainer = academyViewModel.container
    val academyState = academyContainer.stateFlow.collectAsState().value
    var isLoading by remember { mutableStateOf(true) }
    var isSelected by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        quizViewModel.pokemonNameQuiz {
            isLoading = false
        }
    }
    PokemonBackground(isLoading = isLoading) {
        RemoveOverScrollLazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 20.dp)
                ) {
                    PokemonText(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .align(Alignment.TopEnd),
                        text = "${quizId + 1} / 20"
                    )
                    PokemonText(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .align(Alignment.TopStart),
                        text = "이 포켓몬은 뭘까요?"
                    )
                    PokemonIcon(
                        modifier = Modifier
                            .background(Color.Gray, CircleShape)
                            .align(Alignment.Center)
                            .size(100.dp)
                            .padding(20.dp)
                            .pokemonClickable {
                                MediaPlayer().apply {
                                    setDataSource(quizState.soundUrl)
                                    prepare()
                                    start()
                                }
                            },
                        icon = PokemonIconList.PlaySound,
                    )
                }
            }
            items(quizState.quizList) {
                QuizNameItem(
                    item = it,
                    answerId = quizState.pokemonId,
                    isSelected = isSelected,
                    onSelected = { isSelected = true }
                ) {
                    academyViewModel.addAnswer(isAnswer = it)
                    navigateQuiz(academyState.quizLevel, quizId + 1)
                }
            }
        }
    }
}