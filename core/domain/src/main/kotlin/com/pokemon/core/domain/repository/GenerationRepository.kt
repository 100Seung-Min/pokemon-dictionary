package com.pokemon.core.domain.repository

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.DetailGenerationEntity
import com.pokemon.core.domain.entity.GenerationEntity
import kotlinx.coroutines.flow.Flow

interface GenerationRepository {
    suspend fun getGenerationList(): Flow<PagingData<GenerationEntity>>

    suspend fun getGenerationDetail(generationId: Int): DetailGenerationEntity
}