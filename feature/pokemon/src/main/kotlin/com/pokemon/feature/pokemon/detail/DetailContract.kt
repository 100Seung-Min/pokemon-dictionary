package com.pokemon.feature.pokemon.detail

import com.pokemon.core.domain.entity.DetailPokemonEntity

data class DetailState(
    val id: Int = 0,
    val profileUrl: String = "",
    val name: String = "",
    val genus: String = "",
    val flavorList: List<String> = listOf(),
)