package com.pokemon.feature.academy.easy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.component.RemoveOverScrollLazyColumn
import com.pokemon.feature.academy.navigation.AcademyNavigationItem
import com.pokemon.core.ui.component.QuizItem
import com.pokemon.core.ui.util.getActivity
import com.pokemon.feature.academy.academy.AcademyViewModel

@Composable
fun EasyScreen(
    navigateQuiz: (String, Int) -> Unit,
    quizId: Int,
    academyViewModel: AcademyViewModel = hiltViewModel(getActivity()),
) {
    val container = academyViewModel.container
    val state = container.stateFlow.collectAsState().value
    var isLoading by remember { mutableStateOf(true) }
    var isSelected by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        academyViewModel.pokemonNameQuiz {
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
                    AsyncImage(
                        modifier = Modifier.align(Alignment.Center),
                        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${state.answerId}.png",
                        contentDescription = null
                    )
                }
            }
            items(state.quizList) {
                QuizItem(
                    item = it,
                    answerId = state.answerId,
                    isSelected = isSelected,
                    onSelected = { isSelected = true }
                ) {
                    academyViewModel.addAnswer(isAnswer = it)
                    navigateQuiz(AcademyNavigationItem.Easy.route, quizId + 1)
                }
            }
        }
    }
}