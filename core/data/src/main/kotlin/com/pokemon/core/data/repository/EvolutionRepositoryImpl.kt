package com.pokemon.core.data.repository

import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.repository.EvolutionRepository
import com.pokemon.core.remote.datasource.evolution.EvolutionRemoteDataSource
import com.pokemon.core.remote.response.toEntity
import javax.inject.Inject

class EvolutionRepositoryImpl @Inject constructor(
    private val evolutionRemoteDataSource: EvolutionRemoteDataSource,
) : EvolutionRepository {
    override suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionEntity =
        evolutionRemoteDataSource.getEvolutionInfo(evolutionId = evolutionId).toEntity()
}