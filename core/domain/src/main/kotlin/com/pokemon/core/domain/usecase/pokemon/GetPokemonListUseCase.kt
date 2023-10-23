package com.pokemon.core.domain.usecase.pokemon

import com.pokemon.core.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        pokemonRepository.getPokemonList()
    }
}