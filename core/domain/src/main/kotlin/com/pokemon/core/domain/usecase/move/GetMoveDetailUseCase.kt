package com.pokemon.core.domain.usecase.move

import com.pokemon.core.domain.repository.MoveRepository
import javax.inject.Inject

class GetMoveDetailUseCase @Inject constructor(
    private val moveRepository: MoveRepository,
) {
    suspend operator fun invoke(moveId: Int) = kotlin.runCatching {
        moveRepository.getMoveDetail(moveId = moveId)
    }
}