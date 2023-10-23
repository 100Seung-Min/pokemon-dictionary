package com.pokemon.core.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemon.core.remote.api.PokemonAPI
import com.pokemon.core.remote.pagingsource.PokemonPagingSource
import com.pokemon.core.remote.response.DetailPokemonResponse
import com.pokemon.core.remote.response.PokemonResponse
import com.pokemon.core.remote.util.pokemonApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonAPI: PokemonAPI,
) : PokemonRemoteDataSource {
    override suspend fun getPokemonList(): Flow<PagingData<PokemonResponse>> =
        Pager(config = PagingConfig(pageSize = 20), pagingSourceFactory = {
            PokemonPagingSource(pokemonAPI = pokemonAPI)
        }).flow

    override suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonResponse = pokemonApiCall {
        pokemonAPI.getPokemonDetail(pokemonId = pokemonId)
    }
}