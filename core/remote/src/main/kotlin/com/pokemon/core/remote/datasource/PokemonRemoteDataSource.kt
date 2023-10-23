package com.pokemon.core.remote.datasource

import androidx.paging.PagingData
import com.pokemon.core.remote.response.DetailPokemonResponse
import com.pokemon.core.remote.response.InfoPokemonResponse
import com.pokemon.core.remote.response.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(): Flow<PagingData<PokemonResponse>>

    suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonResponse

    suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonResponse
}