package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.remote.response.item.DetailItemResponse
import com.pokemon.core.data.mapper.util.getId
import com.pokemon.core.remote.response.util.URLResponse

fun URLResponse.toItemEntity() = ItemEntity(
    id = url.getId(),
)

fun DetailItemResponse.toEntity(languageId: String) = DetailItemEntity(
    name = nameList.getName(languageId = languageId, defaultValue = name),
    flavorList = flavorList.toFlavorList(languageId = languageId),
    imageUrl = sprites.imageUrl ?: ""
)