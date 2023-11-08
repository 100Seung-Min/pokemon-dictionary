package com.pokemon.core.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemon.core.data.remote.datasource.GenerationRemoteDataSource
import com.pokemon.core.remote.api.GenerationAPI
import com.pokemon.core.remote.pagingsource.GenerationPagingSource
import com.pokemon.core.data.remote.response.generation.DetailGenerationResponse
import com.pokemon.core.data.remote.response.util.URLResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import com.pokemon.core.data.remote.util.pokemonApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerationRemoteDataSourceImpl @Inject constructor(
    private val generationAPI: GenerationAPI,
) : GenerationRemoteDataSource {
    override suspend fun getGenerationList(): Flow<PagingData<URLResponse>> =
        Pager(config = PagingConfig(pageSize = PAGING_SIZE), pagingSourceFactory = {
            GenerationPagingSource(generationAPI = generationAPI)
        }).flow

    override suspend fun getGenerationDetail(generationId: Int): DetailGenerationResponse =
        pokemonApiCall {
            generationAPI.getGenerationDetail(generationId = generationId)
        }
}