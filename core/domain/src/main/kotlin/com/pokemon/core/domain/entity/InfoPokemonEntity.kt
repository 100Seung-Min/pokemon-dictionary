package com.pokemon.core.domain.entity

data class InfoPokemonEntity(
    val weight: Int,
    val height: Int,
    val typeList: List<String>,
    val moveList: List<String>,
)
