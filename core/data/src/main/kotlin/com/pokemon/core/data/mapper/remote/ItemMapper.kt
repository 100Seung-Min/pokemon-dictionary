package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.remote.response.item.DetailItemResponse
import com.pokemon.core.remote.response.item.ItemResponse

fun ItemResponse.toEntity() = ItemEntity(
    id = id,
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/$name.png"
)

fun DetailItemResponse.toEntity(languageId: String) = DetailItemEntity(
    name = nameList.first { it.language.name == languageId }.name,
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)