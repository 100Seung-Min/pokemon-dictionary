package com.pokemon.feature.item.item

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.domain.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

data class ItemState(
    val itemPager: Flow<PagingData<ItemEntity>>? = null,
    val itemDetailList: Map<Int, DetailItemEntity> = mapOf(),
)