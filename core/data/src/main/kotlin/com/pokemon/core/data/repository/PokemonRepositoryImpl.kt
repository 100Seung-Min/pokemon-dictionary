package com.pokemon.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.entity.InfoPokemonEntity
import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.domain.repository.PokemonRepository
import com.pokemon.core.local.datasource.SystemLocalDataSource
import com.pokemon.core.remote.datasource.PokemonRemoteDataSource
import com.pokemon.core.remote.response.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val systemLocalDataSource: SystemLocalDataSource,
) : PokemonRepository {
    override suspend fun getPokemonList(): Flow<PagingData<PokemonEntity>> =
        pokemonRemoteDataSource.getPokemonList().map { it.map { it.toEntity() } }

    override suspend fun getPokemonInfo(pokemonId: Int): InfoPokemonEntity =
        pokemonRemoteDataSource.getPokemonInfo(pokemonId = pokemonId).toEntity()

    override suspend fun getPokemonDetail(pokemonId: Int): DetailPokemonEntity {
        var languageId = systemLocalDataSource.fetchLanguage() ?: "ko"
        if (languageId == "ja") languageId = "ja-Hrkt"
        else if (languageId == "zh") languageId = "zh-Hant"
        return pokemonRemoteDataSource.getPokemonDetail(pokemonId = pokemonId)
            .toEntity(languageId = languageId)
    }

    override suspend fun getMoveDetail(moveId: Int): DetailMoveEntity {
        var languageId = systemLocalDataSource.fetchLanguage() ?: "ko"
        if (languageId == "ja") languageId = "ja-Hrkt"
        else if (languageId == "zh") languageId = "zh-Hant"
        return pokemonRemoteDataSource.getMoveDetail(moveId = moveId)
            .toEntity(languageId = languageId)
    }

    override suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionEntity =
        pokemonRemoteDataSource.getEvolutionInfo(evolutionId = evolutionId).toEntity()

    override suspend fun getItemList(): Flow<PagingData<ItemEntity>> =
        pokemonRemoteDataSource.getItemList().map { it.map { it.toEntity() } }
}