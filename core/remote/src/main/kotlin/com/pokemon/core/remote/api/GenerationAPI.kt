package com.pokemon.core.remote.api

import com.pokemon.core.data.remote.response.generation.DetailGenerationResponse
import com.pokemon.core.data.remote.response.util.PagingResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenerationAPI {
    @GET("generation")
    suspend fun getGenerationList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = PAGING_SIZE,
    ): PagingResponse

    @GET("generation/{generationId}")
    suspend fun getGenerationDetail(
        @Path("generationId") generationId: Int,
    ): DetailGenerationResponse
}