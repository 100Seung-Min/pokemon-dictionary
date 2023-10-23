package com.pokemon.core.domain.usecase.pokemon

import com.pokemon.core.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(pokemonId: Int) = kotlin.runCatching {
        pokemonRepository.getPokemonDetail(pokemonId = pokemonId)
    }
}