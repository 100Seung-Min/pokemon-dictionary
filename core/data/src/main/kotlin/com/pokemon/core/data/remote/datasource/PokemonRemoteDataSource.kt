package com.pokemon.core.data.remote.datasource

import androidx.paging.PagingData
import com.pokemon.core.data.remote.response.pokemon.DetailPokemonResponse
import com.pokemon.core.data.remote.response.pokemon.InfoPokemonResponse
import com.pokemon.core.data.remote.response.util.URLResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(): Flow<PagingData<URLResponse>>

    suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonResponse

    suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonResponse
}