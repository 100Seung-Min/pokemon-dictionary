package com.pokemon.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.domain.repository.PokemonRepository
import com.pokemon.core.remote.datasource.PokemonRemoteDataSource
import com.pokemon.core.remote.response.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
) : PokemonRepository {
    override suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>> =
        pokemonRemoteDataSource.getPokemonList().map { it.map { it.toEntity() } }

    override suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonEntity =
        pokemonRemoteDataSource.getPokemonDetail(pokemonId = pokemonId).toEntity()
}