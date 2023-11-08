package com.pokemon.core.data.remote.datasource

import androidx.paging.PagingData
import com.pokemon.core.data.remote.response.item.DetailItemResponse
import com.pokemon.core.data.remote.response.util.URLResponse
import kotlinx.coroutines.flow.Flow

interface ItemRemoteDataSource {
    suspend fun getItemList(): Flow<PagingData<URLResponse>>

    suspend fun getItemDetail(itemId: Int): DetailItemResponse
}