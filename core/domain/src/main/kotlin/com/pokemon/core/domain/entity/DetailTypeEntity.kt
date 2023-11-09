package com.pokemon.core.domain.entity

data class DetailTypeEntity(
    val id: Int,
    val name: String,
    val pokemonList: List<PokemonEntity>,
)
