package com.pokemon.feature.academy.easy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.ui.component.QuizItem
import com.pokemon.core.ui.util.getActivity
import com.pokemon.feature.academy.academy.AcademyViewModel

@Composable
fun EasyScreen(
    academyViewModel: AcademyViewModel = hiltViewModel(getActivity()),
) {
    val container = academyViewModel.container
    val state = container.stateFlow.collectAsState().value
    LaunchedEffect(Unit) {
        academyViewModel.getEasyQuiz(0)
    }
    PokemonBackground {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier.align(Alignment.Center),
                        model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${state.quizList[0]}.png",
                        contentDescription = null
                    )
                }
            }
            items(state.easyQuiz) {
                QuizItem(item = it) {

                }
            }
        }
    }
}