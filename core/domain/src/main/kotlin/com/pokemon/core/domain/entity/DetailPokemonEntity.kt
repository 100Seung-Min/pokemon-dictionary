package com.pokemon.core.domain.entity

data class DetailPokemonEntity(
    val id: Int,
    val evolutionId: Int,
    val englishName: String,
    val name: String,
    val genus: String,
    val flavorList: List<String>,
)