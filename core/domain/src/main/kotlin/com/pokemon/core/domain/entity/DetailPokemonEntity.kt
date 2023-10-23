package com.pokemon.core.domain.entity

data class DetailPokemonEntity(
    val id: Int,
    val profileUrl: String,
    val name: String,
    val genus: String,
    val flavorList: List<String>,
)