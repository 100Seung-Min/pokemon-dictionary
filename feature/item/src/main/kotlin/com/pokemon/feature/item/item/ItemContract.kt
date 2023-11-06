package com.pokemon.feature.item.item

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

data class ItemState(
    val itemList: Flow<PagingData<ItemEntity>>? = null,
)