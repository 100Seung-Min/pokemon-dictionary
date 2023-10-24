package com.pokemon.core.domain.usecase.pokemon

import com.pokemon.core.domain.repository.PokemonRepository
import javax.inject.Inject

class GetMoveDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(moveId: Int) = kotlin.runCatching {
        pokemonRepository.getMoveDetail(moveId = moveId)
    }
}