package com.pokemon.core.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokemon.core.remote.api.ItemAPI
import com.pokemon.core.remote.response.ItemResponse
import com.pokemon.core.remote.response.toItemResponse
import com.pokemon.core.remote.util.PAGING_SIZE

class ItemPagingSource(
    private val itemAPI: ItemAPI,
) : PagingSource<Int, ItemResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ItemResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemResponse> {
        return try {
            val page = params.key ?: 0
            val response = itemAPI.getItemList(offset = page * PAGING_SIZE)

            LoadResult.Page(
                data = response.result.map { it.toItemResponse() },
                prevKey = if (response.previous == null) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}