package com.pokemon.feature.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pokemon.core.navigation.pokemon.PokemonDeepLinkKey
import com.pokemon.core.navigation.pokemon.PokemonNavigationItem
import com.pokemon.core.ui.component.PokemonItem
import com.pokemon.core.ui.util.getActivity
import com.pokemon.core.ui.util.toPokemonType

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(getActivity()),
) {
    val container = homeViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val pokemonPager = state.pokemonListPager?.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        pokemonPager?.let {
            when (it.loadState.refresh) {
                is LoadState.Loading -> {}
                is LoadState.Error -> {}
                else -> {
                    LazyVerticalGrid(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(it.itemCount) { index ->
                            it[index]?.let {
                                LaunchedEffect(Unit) {
                                    homeViewModel.getPokemonInfo(pokemonId = it.id)
                                }
                                PokemonItem(
                                    name = state.pokemonList[it.id],
                                    imageUrl = it.profileUrl,
                                    backgroundColor = state.typeList[it.id]?.toPokemonType()?.typeColor
                                ) {
                                    navController.navigate(PokemonNavigationItem.Detail.route + PokemonDeepLinkKey.ID + it.id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}