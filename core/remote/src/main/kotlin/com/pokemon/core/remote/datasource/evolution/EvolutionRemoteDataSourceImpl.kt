package com.pokemon.core.remote.datasource.evolution

import com.pokemon.core.remote.api.EvolutionAPI
import com.pokemon.core.remote.response.evolution.InfoEvolutionResponse
import com.pokemon.core.remote.util.pokemonApiCall
import javax.inject.Inject

class EvolutionRemoteDataSourceImpl @Inject constructor(
    private val evolutionAPI: EvolutionAPI,
) : EvolutionRemoteDataSource {
    override suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionResponse =
        pokemonApiCall {
            evolutionAPI.getEvolutionInfo(evolutionId = evolutionId)
        }
}