package com.pokemon.core.domain.repository

import com.pokemon.core.domain.entity.InfoEvolutionEntity

interface EvolutionRepository {
    suspend fun getEvolutionInfo(evolutionId: Int): InfoEvolutionEntity
}