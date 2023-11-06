package com.pokemon.core.domain.usecase.pokemon

import com.pokemon.core.domain.repository.PokemonRepository
import javax.inject.Inject

class GetItemDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(itemId: Int) = kotlin.runCatching {
        pokemonRepository.getItemDetail(itemId = itemId)
    }
}