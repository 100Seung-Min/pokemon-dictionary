package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.remote.response.move.DetailMoveResponse

fun DetailMoveResponse.toEntity(languageId: String) = DetailMoveEntity(
    name = nameList.getName(languageId = languageId, defaultValue = ""),
    type = type.name,
    flavorList = flavorList.toFlavorTextList(languageId = languageId)
)