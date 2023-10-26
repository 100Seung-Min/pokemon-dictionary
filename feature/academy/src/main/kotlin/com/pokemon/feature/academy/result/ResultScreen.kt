package com.pokemon.feature.academy.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.ui.util.getActivity
import com.pokemon.feature.academy.academy.AcademyViewModel

@Composable
fun ResultScreen(
    academyViewModel: AcademyViewModel = hiltViewModel(getActivity()),
) {
    val container = academyViewModel.container
    val state = container.stateFlow.collectAsState().value
    PokemonBackground {
        PokemonText(text = state.answerList.count { it }.toString())
    }
}