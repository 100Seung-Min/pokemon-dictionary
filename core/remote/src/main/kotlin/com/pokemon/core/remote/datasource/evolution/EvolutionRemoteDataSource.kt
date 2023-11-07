package com.pokemon.core.remote.datasource.evolution

import com.pokemon.core.remote.response.InfoEvolutionResponse

interface EvolutionRemoteDataSource {
    suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionResponse
}