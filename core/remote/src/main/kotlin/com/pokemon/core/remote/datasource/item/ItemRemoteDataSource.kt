package com.pokemon.core.remote.datasource.item

import androidx.paging.PagingData
import com.pokemon.core.remote.response.item.DetailItemResponse
import com.pokemon.core.remote.response.item.ItemResponse
import kotlinx.coroutines.flow.Flow

interface ItemRemoteDataSource {
    suspend fun getItemList(): Flow<PagingData<ItemResponse>>

    suspend fun getItemDetail(itemId: Int): DetailItemResponse
}