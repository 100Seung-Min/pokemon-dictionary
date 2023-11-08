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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonBottomSheet
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.component.RemoveOverScrollLazyVerticalGrid
import com.pokemon.core.design_system.R
import com.pokemon.core.ui.component.AttributeFilterItem
import com.pokemon.core.ui.component.GenerationItem
import com.pokemon.core.ui.component.PokemonItem
import com.pokemon.core.ui.util.filterType
import com.pokemon.core.ui.util.getActivity
import com.pokemon.core.ui.util.pokemonClickable
import com.pokemon.core.ui.util.toPokemonType
import com.pokemon.core.ui.util.typeList

@Composable
fun HomeScreen(
    navigatePokemonDetail: (Int) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(getActivity()),
) {
    val container = homeViewModel.container
    val state = container.stateFlow.collectAsState().value
    val pokemonPager = state.pokemonListPager?.collectAsLazyPagingItems()
    val generationPager = state.generationListPager?.collectAsLazyPagingItems()

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
                Row {
                    PokemonText(text = stringResource(id = R.string.type))
                    Spacer(modifier = Modifier.width(12.dp))
                    if (state.selectedTypeList.isNotEmpty()) {
                        PokemonText(
                            modifier = Modifier.pokemonClickable { homeViewModel.clearSelectTypeList() },
                            text = stringResource(id = R.string.cancel_select)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                RemoveOverScrollLazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(typeList) {
                        AttributeFilterItem(
                            isSelected = state.selectedTypeList.contains(it),
                            typeString = it
                        ) {
                            homeViewModel.changeSelectTypeList(selectType = it)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row {
                    PokemonText(text = stringResource(id = R.string.generation))
                    Spacer(modifier = Modifier.width(12.dp))
                    if (state.selectedGenerationList.isNotEmpty()) {
                        PokemonText(
                            modifier = Modifier.pokemonClickable { homeViewModel.clearSelectGenerationList() },
                            text = stringResource(id = R.string.cancel_select)
                        )
                    }
                }
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
                            homeViewModel.changeSelectGenerationList(selectGeneration = it)
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
                    PokemonText(text = stringResource(id = R.string.filter))
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            if (state.selectedGenerationList.isEmpty()) {
                pokemonPager?.let {
                    when (it.loadState.refresh) {
                        is LoadState.Loading -> {}
                        is LoadState.Error -> {}
                        else -> {
                            LaunchedEffect(it.itemCount) {
                                for (index in 0 until it.itemCount) {
                                    it[index]?.let { homeViewModel.getPokemonInfo(pokemonId = it.id) }
                                }
                            }
                            PokemonList {
                                items(
                                    it.itemSnapshotList.items.filterType(
                                        state.typeList,
                                        state.selectedTypeList
                                    )
                                ) { item ->
                                    PokemonItem(
                                        name = state.pokemonList[item.id],
                                        imageUrl = item.profileUrl,
                                        backgroundColor = state.typeList[item.id]?.get(0)
                                            ?.toPokemonType()?.typeColor
                                    ) {
                                        navigatePokemonDetail(item.id)
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                state.selectedGenerationList.forEach {
                    for (index in 0 until it.pokemonList.size) {
                        LaunchedEffect(Unit) {
                            homeViewModel.getPokemonInfo(pokemonId = it.pokemonList[index].id)
                        }
                    }
                }
                PokemonList {
                    state.selectedGenerationList.forEach {
                        items(it.pokemonList.filterType(state.typeList, state.selectedTypeList)) {
                            PokemonItem(
                                name = state.pokemonList[it.id],
                                imageUrl = it.profileUrl,
                                backgroundColor = state.typeList[it.id]?.get(0)
                                    ?.toPokemonType()?.typeColor
                            ) {
                                navigatePokemonDetail(it.id)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonList(
    content: LazyGridScope.() -> Unit,
) {
    RemoveOverScrollLazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 15.dp),
        contentPadding = PaddingValues(vertical = 10.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        content = content
    )
}