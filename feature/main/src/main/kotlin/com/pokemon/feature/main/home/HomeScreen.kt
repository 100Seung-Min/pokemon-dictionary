package com.pokemon.feature.main.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.pokemon.core.navigation.pokemon.PokemonDeepLinkKey
import com.pokemon.core.navigation.pokemon.PokemonNavigationItem
import com.pokemon.core.ui.util.getActivity

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
                    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                        items(it.itemCount) { index ->
                            it[index]?.let {
                                AsyncImage(
                                    modifier = Modifier.clickable {
                                        navController.navigate(PokemonNavigationItem.Detail.route + PokemonDeepLinkKey.ID + it.id)
                                    },
                                    model = it.profileUrl,
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}