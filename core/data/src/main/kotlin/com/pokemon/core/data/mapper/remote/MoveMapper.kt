package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.remote.response.move.DetailMoveResponse

fun DetailMoveResponse.toEntity(languageId: String) = DetailMoveEntity(
    name = nameList.first { it.language.name == languageId }.name,
    type = type.name,
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)