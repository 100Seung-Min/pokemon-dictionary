package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.remote.response.item.DetailItemResponse
import com.pokemon.core.data.mapper.util.getId
import com.pokemon.core.data.mapper.util.toItemImageUrl
import com.pokemon.core.remote.response.util.URLResponse

fun URLResponse.toItemEntity() = ItemEntity(
    id = url.getId(),
    imageUrl = name.toItemImageUrl()
)

fun DetailItemResponse.toEntity(languageId: String) = DetailItemEntity(
    name = nameList.getName(languageId = languageId, defaultValue = ""),
    flavorList = flavorList.toFlavorList(languageId = languageId)
)