package com.pokemon.core.remote.datasource.pokemon

import androidx.paging.PagingData
import com.pokemon.core.remote.response.pokemon.DetailPokemonResponse
import com.pokemon.core.remote.response.pokemon.InfoPokemonResponse
import com.pokemon.core.remote.response.pokemon.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(): Flow<PagingData<PokemonResponse>>

    suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonResponse

    suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonResponse
}