package com.pokemon.core.domain.usecase.evolution

import com.pokemon.core.domain.repository.EvolutionRepository
import javax.inject.Inject

class GetEvolutionInfoUseCase @Inject constructor(
    private val evolutionRepository: EvolutionRepository,
) {
    suspend operator fun invoke(evolutionId: Int) = kotlin.runCatching {
        evolutionRepository.getEvolutionInfo(evolutionId = evolutionId)
    }
}