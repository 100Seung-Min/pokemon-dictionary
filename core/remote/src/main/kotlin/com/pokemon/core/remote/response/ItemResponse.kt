package com.pokemon.core.remote.response

import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.remote.util.getId

data class ItemResponse(
    val id: Int,
    val name: String,
)

fun PagingItemResponse.Result.toResponse() = ItemResponse(
    id = url.getId(),
    name = name
)

fun ItemResponse.toEntity() = ItemEntity(
    id = id,
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/$name.png"
)