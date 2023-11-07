package com.pokemon.core.remote.datasource.pokemon

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemon.core.remote.api.PokemonAPI
import com.pokemon.core.remote.pagingsource.PokemonPagingSource
import com.pokemon.core.remote.response.pokemon.DetailPokemonResponse
import com.pokemon.core.remote.response.pokemon.InfoPokemonResponse
import com.pokemon.core.remote.response.util.URLResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import com.pokemon.core.remote.util.pokemonApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonAPI: PokemonAPI,
) : PokemonRemoteDataSource {
    override suspend fun getPokemonList(): Flow<PagingData<URLResponse>> =
        Pager(config = PagingConfig(pageSize = PAGING_SIZE), pagingSourceFactory = {
            PokemonPagingSource(pokemonAPI = pokemonAPI)
        }).flow

    override suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonResponse = pokemonApiCall {
        pokemonAPI.getPokemonInfo(pokemonId = pokemonId)
    }

    override suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonResponse = pokemonApiCall {
        pokemonAPI.getPokemonDetail(pokemonId = pokemonId)
    }
}