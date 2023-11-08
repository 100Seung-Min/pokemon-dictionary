package com.pokemon.core.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemon.core.data.remote.datasource.ItemRemoteDataSource
import com.pokemon.core.remote.api.ItemAPI
import com.pokemon.core.remote.pagingsource.ItemPagingSource
import com.pokemon.core.data.remote.response.item.DetailItemResponse
import com.pokemon.core.data.remote.response.util.URLResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import com.pokemon.core.data.remote.util.pokemonApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRemoteDataSourceImpl @Inject constructor(
    private val itemAPI: ItemAPI,
) : ItemRemoteDataSource {
    override suspend fun getItemList(): Flow<PagingData<URLResponse>> =
        Pager(config = PagingConfig(pageSize = PAGING_SIZE), pagingSourceFactory = {
            ItemPagingSource(itemAPI = itemAPI)
        }).flow

    override suspend fun getItemDetail(itemId: Int): DetailItemResponse =
        pokemonApiCall { itemAPI.getItemDetail(itemId = itemId) }
}