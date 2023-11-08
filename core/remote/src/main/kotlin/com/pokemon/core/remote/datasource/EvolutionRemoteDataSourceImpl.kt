package com.pokemon.core.remote.datasource

import com.pokemon.core.data.remote.datasource.EvolutionRemoteDataSource
import com.pokemon.core.remote.api.EvolutionAPI
import com.pokemon.core.data.remote.response.evolution.InfoEvolutionResponse
import com.pokemon.core.data.remote.util.pokemonApiCall
import javax.inject.Inject

class EvolutionRemoteDataSourceImpl @Inject constructor(
    private val evolutionAPI: EvolutionAPI,
) : EvolutionRemoteDataSource {
    override suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionResponse =
        pokemonApiCall {
            evolutionAPI.getEvolutionInfo(evolutionId = evolutionId)
        }
}