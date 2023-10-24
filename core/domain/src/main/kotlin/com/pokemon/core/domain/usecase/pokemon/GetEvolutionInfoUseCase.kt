package com.pokemon.core.domain.usecase.pokemon

import com.pokemon.core.domain.repository.PokemonRepository
import javax.inject.Inject

class GetEvolutionInfoUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(evolutionId: Int) = kotlin.runCatching {
        pokemonRepository.getEvolutionInfo(evolutionId = evolutionId)
    }
}