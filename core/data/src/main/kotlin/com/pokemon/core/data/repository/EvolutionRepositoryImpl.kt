package com.pokemon.core.data.repository

import com.pokemon.core.data.mapper.remote.toEntity
import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.repository.EvolutionRepository
import com.pokemon.core.remote.datasource.evolution.EvolutionRemoteDataSource
import javax.inject.Inject

class EvolutionRepositoryImpl @Inject constructor(
    private val evolutionRemoteDataSource: EvolutionRemoteDataSource,
) : EvolutionRepository {
    override suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionEntity =
        evolutionRemoteDataSource.getEvolutionInfo(evolutionId = evolutionId).toEntity()
}