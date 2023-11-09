package com.pokemon.core.domain.usecase.type

import com.pokemon.core.domain.repository.TypeRepository
import javax.inject.Inject

class GetTypeDetailUseCase @Inject constructor(
    private val typeRepository: TypeRepository,
) {
    suspend operator fun invoke(typeId: Int) = kotlin.runCatching {
        typeRepository.getTypeDetail(typeId = typeId)
    }
}