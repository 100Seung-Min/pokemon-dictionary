package com.pokemon.core.data.repository

import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.repository.EvolutionRepository
import com.pokemon.core.data.remote.datasource.EvolutionRemoteDataSource
import com.pokemon.core.data.remote.response.evolution.toEntity
import javax.inject.Inject

class EvolutionRepositoryImpl @Inject constructor(
    private val evolutionRemoteDataSource: EvolutionRemoteDataSource,
) : EvolutionRepository {
    override suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionEntity =
        evolutionRemoteDataSource.getEvolutionInfo(evolutionId = evolutionId).toEntity()
}