package com.pokemon.feature.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonBottomSheet
import com.pokemon.core.design_system.component.RemoveOverScrollLazyVerticalGrid
import com.pokemon.core.navigation.pokemon.PokemonDeepLinkKey
import com.pokemon.core.navigation.pokemon.PokemonNavigationItem
import com.pokemon.core.ui.component.GenerationItem
import com.pokemon.core.ui.component.PokemonItem
import com.pokemon.core.ui.util.getActivity
import com.pokemon.core.ui.util.pokemonClickable
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
    val generationPager = state.generationListPager?.collectAsLazyPagingItems()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        homeViewModel.settingLanguage(context)
    }

    LaunchedEffect(generationPager?.loadState?.refresh) {
        if (generationPager != null) {
            when (generationPager.loadState.refresh) {
                is LoadState.Loading -> {}
                is LoadState.Error -> {}
                else -> {
                    homeViewModel.getGenerationDetail(generationList = generationPager.itemSnapshotList.toList())
                }
            }
        }
    }

    PokemonBottomSheet(
        sheetContent = {
            Column(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 20.dp)
            ) {
                Text(text = "세대")
                Spacer(modifier = Modifier.height(6.dp))
                RemoveOverScrollLazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.generationList) {
                        GenerationItem(
                            name = it.name,
                            isSelected = state.selectedGenerationList.contains(it)
                        ) {
                            if (state.selectedGenerationList.contains(it)) {
                                homeViewModel.removeSelectGenerationList(it)
                            } else {
                                homeViewModel.addSelectGenerationList(it)
                            }
                        }
                    }
                }
            }
        }
    ) { bottomSheetAction ->
        PokemonBackground {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
                        .padding(vertical = 5.dp, horizontal = 12.dp)
                        .pokemonClickable {
                            bottomSheetAction()
                        }
                ) {
                    Text(text = "필터")
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            if (state.selectedGenerationList.isEmpty()) {
                pokemonPager?.let {
                    when (it.loadState.refresh) {
                        is LoadState.Loading -> {}
                        is LoadState.Error -> {}
                        else -> {
                            RemoveOverScrollLazyVerticalGrid(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                contentPadding = PaddingValues(vertical = 10.dp),
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
            } else {
                RemoveOverScrollLazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    contentPadding = PaddingValues(vertical = 10.dp),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    state.selectedGenerationList.forEach {
                        items(it.pokemonList) {
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