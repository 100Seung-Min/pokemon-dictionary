package com.pokemon.feature.item.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.RemoveOverScrollLazyVerticalGrid
import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.ui.util.getActivity
import com.pokemon.core.ui.util.pokemonClickable
import com.pokemon.core.design_system.R

@Composable
fun ItemScreen(
    itemViewModel: ItemViewModel = hiltViewModel(getActivity()),
) {
    val container = itemViewModel.container
    val state = container.stateFlow.collectAsState().value
    val itemPager = state.itemPager?.collectAsLazyPagingItems()
    var isDetailItem by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<DetailItemEntity?>(null) }

    LaunchedEffect(Unit) {
        itemViewModel.getItemList()
    }
    PokemonBackground {
        if (isDetailItem) {
            selectedItem?.let {
                Dialog(onDismissRequest = { isDetailItem = false }) {
                    Column(
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(vertical = 20.dp, horizontal = 24.dp)
                    ) {
                        Text(text = it.name)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = it.flavorList.firstOrNull() ?: "")
                    }
                }
            }
        }
        itemPager?.let {
            when (it.loadState.refresh) {
                is LoadState.Loading -> {}
                is LoadState.Error -> {}
                else -> {
                    for (index in 0 until it.itemCount) {
                        LaunchedEffect(Unit) {
                            it[index]?.let { itemViewModel.getItemDetail(itemId = it.id) }
                        }
                    }
                    RemoveOverScrollLazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(it.itemCount) { index ->
                            it[index]?.let {
                                AsyncImage(
                                    model = state.itemDetailList[it.id]?.imageUrl,
                                    contentDescription = null,
                                    placeholder = painterResource(id = R.drawable.ic_pokemon_placeholder),
                                    error = painterResource(id = R.drawable.ic_pokemon_placeholder),
                                    modifier = Modifier
                                        .size(100.dp)
                                        .pokemonClickable {
                                            isDetailItem = true
                                            selectedItem = state.itemDetailList[it.id]
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}