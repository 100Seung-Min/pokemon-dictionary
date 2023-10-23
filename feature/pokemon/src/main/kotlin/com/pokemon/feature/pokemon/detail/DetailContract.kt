package com.pokemon.feature.pokemon.detail

data class DetailState(
    val id: Int = 0,
    val profileUrl: String = "",
    val name: String = "",
    val weight: Int = 0,
    val height: Int = 0,
    val genus: String = "",
    val typeList: List<String> = listOf(),
    val flavorList: List<String> = listOf(),
)