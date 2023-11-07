package com.pokemon.core.remote.datasource.pokemon

import androidx.paging.PagingData
import com.pokemon.core.remote.response.DetailItemResponse
import com.pokemon.core.remote.response.DetailMoveResponse
import com.pokemon.core.remote.response.DetailPokemonResponse
import com.pokemon.core.remote.response.InfoEvolutionResponse
import com.pokemon.core.remote.response.InfoPokemonResponse
import com.pokemon.core.remote.response.ItemResponse
import com.pokemon.core.remote.response.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(): Flow<PagingData<PokemonResponse>>

    suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonResponse

    suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonResponse

    suspend fun getMoveDetail(moveId: Int): DetailMoveResponse

    suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionResponse
}