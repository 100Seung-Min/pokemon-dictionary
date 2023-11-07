package com.pokemon.core.domain.usecase.generation

import com.pokemon.core.domain.repository.GenerationRepository
import javax.inject.Inject

class GetGenerationDetailUseCase @Inject constructor(
    private val generationRepository: GenerationRepository,
) {
    suspend operator fun invoke(generationId: Int) = kotlin.runCatching {
        generationRepository.getGenerationDetail(generationId = generationId)
    }
}