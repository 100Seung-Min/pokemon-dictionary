package com.pokemon.feature.academy.academy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.RemoveOverScrollLazyColumn
import com.pokemon.core.navigation.academy.AcademyDeepLinkKey
import com.pokemon.core.navigation.academy.AcademyNavigationItem
import com.pokemon.core.ui.component.AcademyMenuItem
import com.pokemon.core.ui.model.AcademyMenuModel
import com.pokemon.core.ui.util.getActivity

@Composable
fun AcademyScreen(
    navController: NavController,
    academyViewModel: AcademyViewModel = hiltViewModel(getActivity()),
) {
    LaunchedEffect(Unit) {
        academyViewModel.setQuizList()
    }

    val iconList = listOf(
        AcademyMenuModel(
            title = "쉬움",
            icon = PokemonIconList.Easy,
            backgroundColor = Color.Gray,
            route = AcademyNavigationItem.Easy.route
        ),
        AcademyMenuModel(
            title = "노말",
            icon = PokemonIconList.Normal,
            backgroundColor = Color.Gray,
            route = AcademyNavigationItem.Normal.route
        ),
        AcademyMenuModel(
            title = "어려움",
            icon = PokemonIconList.Hard,
            backgroundColor = Color.Gray,
            route = AcademyNavigationItem.Hard.route
        ),
    )
    PokemonBackground {
        Spacer(modifier = Modifier.weight(1F))
        RemoveOverScrollLazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(iconList) {
                AcademyMenuItem(item = it) {
                    navController.navigate(it.route + AcademyDeepLinkKey.QuizId + 0)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}