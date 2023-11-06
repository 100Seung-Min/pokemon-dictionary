package com.pokemon.feature.item.item

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.RemoveOverScrollLazyVerticalGrid
import com.pokemon.core.ui.util.getActivity

@Composable
fun ItemScreen(
    itemViewModel: ItemViewModel = hiltViewModel(getActivity()),
) {
    val container = itemViewModel.container
    val state = container.stateFlow.collectAsState().value
    val itemPager = state.itemList?.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        itemViewModel.getItemList()
    }
    PokemonBackground {
        itemPager?.let {
            when (it.loadState.refresh) {
                is LoadState.Loading -> {}
                is LoadState.Error -> {}
                else -> {
                    RemoveOverScrollLazyVerticalGrid(columns = GridCells.Fixed(3)) {
                        items(it.itemCount) { index ->
                            it[index]?.let {
                                AsyncImage(
                                    model = it.imageUrl,
                                    contentDescription = null,
                                    modifier = Modifier.size(100.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}