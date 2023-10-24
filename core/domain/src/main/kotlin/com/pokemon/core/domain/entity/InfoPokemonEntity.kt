package com.pokemon.core.domain.entity

data class InfoPokemonEntity(
    val profileUrl: String,
    val speciesId: Int,
    val weight: Int,
    val height: Int,
    val typeList: List<String>,
    val moveList: List<Int>,
)
