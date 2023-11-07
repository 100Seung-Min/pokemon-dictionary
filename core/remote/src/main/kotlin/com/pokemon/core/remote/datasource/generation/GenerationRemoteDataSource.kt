package com.pokemon.core.remote.datasource.generation

import androidx.paging.PagingData
import com.pokemon.core.remote.response.util.URLResponse
import kotlinx.coroutines.flow.Flow

interface GenerationRemoteDataSource {
    suspend fun getGenerationList(): Flow<PagingData<URLResponse>>
}