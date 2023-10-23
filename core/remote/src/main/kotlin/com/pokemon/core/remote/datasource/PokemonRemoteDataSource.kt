package com.pokemon.core.remote.datasource

import androidx.paging.PagingData
import com.pokemon.core.remote.response.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(): Flow<PagingData<PokemonResponse>>
}