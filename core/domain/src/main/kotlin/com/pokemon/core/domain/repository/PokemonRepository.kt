package com.pokemon.core.domain.repository

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.entity.InfoPokemonEntity
import com.pokemon.core.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>>

    suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonEntity

    suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonEntity

    suspend fun getMoveDetail(moveId: Int): DetailMoveEntity

    suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionEntity
}