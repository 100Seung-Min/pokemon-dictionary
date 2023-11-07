package com.pokemon.core.domain.repository

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.domain.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItemList(): Flow<PagingData<ItemEntity>>

    suspend fun getItemDetail(itemId: Int): DetailItemEntity
}