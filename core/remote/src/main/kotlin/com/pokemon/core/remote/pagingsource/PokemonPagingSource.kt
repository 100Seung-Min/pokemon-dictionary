package com.pokemon.core.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokemon.core.remote.api.PokemonAPI
import com.pokemon.core.remote.response.PokemonResponse
import com.pokemon.core.remote.response.toPokemonResponse
import com.pokemon.core.remote.util.PAGING_SIZE

class PokemonPagingSource(
    private val pokemonAPI: PokemonAPI,
) : PagingSource<Int, PokemonResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResponse> {
        return try {
            val page = params.key ?: 0
            val response = pokemonAPI.getPokemonList(offset = page * PAGING_SIZE)

            LoadResult.Page(
                data = response.result.map { it.toPokemonResponse() },
                prevKey = if (response.previous == null) null else page - 1,
                nextKey = if (response.next == null) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}