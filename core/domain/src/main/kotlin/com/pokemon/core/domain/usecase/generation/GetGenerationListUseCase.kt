package com.pokemon.core.domain.usecase.generation

import com.pokemon.core.domain.repository.GenerationRepository
import javax.inject.Inject

class GetGenerationListUseCase @Inject constructor(
    private val generationRepository: GenerationRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        generationRepository.getGenerationList()
    }
}