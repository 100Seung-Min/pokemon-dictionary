package com.pokemon.core.remote.api

import com.pokemon.core.remote.response.InfoEvolutionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EvolutionAPI {
    @GET("evolution-chain/{evolutionId}")
    suspend fun getEvolutionInfo(
        @Path("evolutionId") evolutionId: Int,
    ): InfoEvolutionResponse
}