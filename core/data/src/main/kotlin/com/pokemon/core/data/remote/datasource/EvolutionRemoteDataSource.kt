package com.pokemon.core.data.remote.datasource

import com.pokemon.core.data.remote.response.evolution.InfoEvolutionResponse

interface EvolutionRemoteDataSource {
    suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionResponse
}