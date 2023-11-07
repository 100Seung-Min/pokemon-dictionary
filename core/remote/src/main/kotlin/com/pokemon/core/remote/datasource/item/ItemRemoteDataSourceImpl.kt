package com.pokemon.core.remote.datasource.item

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemon.core.remote.api.ItemAPI
import com.pokemon.core.remote.pagingsource.ItemPagingSource
import com.pokemon.core.remote.response.DetailItemResponse
import com.pokemon.core.remote.response.ItemResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import com.pokemon.core.remote.util.pokemonApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(
    private val itemAPI: ItemAPI,
) : ItemRemoteDataSource {
    override suspend fun getItemList(): Flow<PagingData<ItemResponse>> =
        Pager(config = PagingConfig(pageSize = PAGING_SIZE), pagingSourceFactory = {
            ItemPagingSource(itemAPI = itemAPI)
        }).flow

    override suspend fun getItemDetail(itemId: Int): DetailItemResponse =
        pokemonApiCall { itemAPI.getItemDetail(itemId = itemId) }
}