package com.pokemon.feature.pokemon.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pokemon.feature.pokemon.util.toPokemonType

@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    val container = detailViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    LaunchedEffect(Unit) {
        detailViewModel.getPokemonDetail(pokemonId = id)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                AsyncImage(model = state.profileUrl, contentDescription = null)
                Text(text = state.name)
                Text(text = "${state.height / 10F}M")
                Text(text = "${state.weight / 10F}KG")
                Text(text = state.genus)
                LazyRow {
                    items(state.typeList) {
                        val type = it.toPokemonType()
                        Text(
                            text = stringResource(id = type.typeId),
                            modifier = Modifier.background(type.typeColor)
                        )
                    }
                }
            }
            items(state.flavorList) {
                Text(text = it)
            }
        }
    }
}