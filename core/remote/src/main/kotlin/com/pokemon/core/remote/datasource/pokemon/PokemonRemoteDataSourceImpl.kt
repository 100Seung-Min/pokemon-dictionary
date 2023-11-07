package com.pokemon.core.remote.datasource.pokemon

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemon.core.remote.api.PokemonAPI
import com.pokemon.core.remote.pagingsource.ItemPagingSource
import com.pokemon.core.remote.pagingsource.PokemonPagingSource
import com.pokemon.core.remote.response.DetailItemResponse
import com.pokemon.core.remote.response.DetailMoveResponse
import com.pokemon.core.remote.response.DetailPokemonResponse
import com.pokemon.core.remote.response.InfoEvolutionResponse
import com.pokemon.core.remote.response.InfoPokemonResponse
import com.pokemon.core.remote.response.ItemResponse
import com.pokemon.core.remote.response.PokemonResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import com.pokemon.core.remote.util.pokemonApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonAPI: PokemonAPI,
) : PokemonRemoteDataSource {
    override suspend fun getPokemonList(): Flow<PagingData<PokemonResponse>> =
        Pager(config = PagingConfig(pageSize = PAGING_SIZE), pagingSourceFactory = {
            PokemonPagingSource(pokemonAPI = pokemonAPI)
        }).flow

    override suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonResponse = pokemonApiCall {
        pokemonAPI.getPokemonInfo(pokemonId = pokemonId)
    }

    override suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonResponse = pokemonApiCall {
        pokemonAPI.getPokemonDetail(pokemonId = pokemonId)
    }

    override suspend fun getMoveDetail(moveId: Int): DetailMoveResponse = pokemonApiCall {
        pokemonAPI.getMoveDetail(moveId = moveId)
    }

    override suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionResponse =
        pokemonApiCall {
            pokemonAPI.getEvolutionInfo(evolutionId = evolutionId)
        }
}