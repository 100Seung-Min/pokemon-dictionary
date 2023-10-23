package com.pokemon.core.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemon.core.remote.api.PokemonAPI
import com.pokemon.core.remote.pagingsource.PokemonPagingSource
import com.pokemon.core.remote.response.PokemonResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonAPI: PokemonAPI,
) : PokemonRemoteDataSource {
    override suspend fun getPokemonList(): Flow<PagingData<PokemonResponse>> =
        Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = {
            PokemonPagingSource(pokemonAPI = pokemonAPI)
        }).flow

}