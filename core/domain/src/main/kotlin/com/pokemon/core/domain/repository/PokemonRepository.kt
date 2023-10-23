package com.pokemon.core.domain.repository

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>>

    suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonEntity
}