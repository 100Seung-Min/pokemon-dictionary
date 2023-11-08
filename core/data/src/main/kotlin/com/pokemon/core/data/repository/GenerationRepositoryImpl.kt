package com.pokemon.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.pokemon.core.data.mapper.remote.toEntity
import com.pokemon.core.data.mapper.remote.toGenerationEntity
import com.pokemon.core.domain.entity.DetailGenerationEntity
import com.pokemon.core.domain.entity.GenerationEntity
import com.pokemon.core.domain.repository.GenerationRepository
import com.pokemon.core.data.local.datasource.SystemLocalDataSource
import com.pokemon.core.remote.datasource.generation.GenerationRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GenerationRepositoryImpl @Inject constructor(
    private val generationRemoteDataSource: GenerationRemoteDataSource,
    private val systemLocalDataSource: SystemLocalDataSource,
) : GenerationRepository {
    override suspend fun getGenerationList(): Flow<PagingData<GenerationEntity>> =
        generationRemoteDataSource.getGenerationList().map { it.map { it.toGenerationEntity() } }

    override suspend fun getGenerationDetail(generationId: Int): DetailGenerationEntity =
        generationRemoteDataSource.getGenerationDetail(generationId = generationId)
            .toEntity(languageId = systemLocalDataSource.fetchLanguageId())
}