package com.pokemon.feature.academy.academy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.RemoveOverScrollLazyColumn
import com.pokemon.core.design_system.R
import com.pokemon.core.ui.component.item.AcademyMenuItem
import com.pokemon.core.ui.model.AcademyMenuModel
import com.pokemon.core.ui.util.getActivity
import com.pokemon.feature.academy.navigation.QuizLevel

@Composable
fun AcademyScreen(
    navigateQuiz: (QuizLevel, Int) -> Unit,
    academyViewModel: AcademyViewModel = hiltViewModel(getActivity()),
) {
    val iconList = listOf(
        AcademyMenuModel(
            title = stringResource(id = R.string.easy),
            icon = PokemonIconList.Easy,
            backgroundColor = Color.Gray,
        ),
        AcademyMenuModel(
            title = stringResource(id = R.string.medium),
            icon = PokemonIconList.Normal,
            backgroundColor = Color.Gray,
        ),
        AcademyMenuModel(
            title = stringResource(id = R.string.hard),
            icon = PokemonIconList.Hard,
            backgroundColor = Color.Gray,
        ),
    )
    PokemonBackground {
        Spacer(modifier = Modifier.weight(1F))
        RemoveOverScrollLazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(iconList) { index, item ->
                AcademyMenuItem(item = item) {
                    val quizLevel = QuizLevel.values()[index]
                    academyViewModel.initQuiz(quizLevel = quizLevel)
                    navigateQuiz(quizLevel, 0)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}