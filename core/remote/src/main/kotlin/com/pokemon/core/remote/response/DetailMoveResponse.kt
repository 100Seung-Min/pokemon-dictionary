package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.remote.response.util.FlavorTextResponse
import com.pokemon.core.remote.response.util.NameResponse
import com.pokemon.core.remote.response.util.TypeResponse

data class DetailMoveResponse(
    @SerializedName("flavor_text_entries")
    val flavorList: List<FlavorTextResponse>,
    @SerializedName("type")
    val type: TypeResponse,
    @SerializedName("names")
    val nameList: List<NameResponse>,
)

fun DetailMoveResponse.toEntity(languageId: String) = DetailMoveEntity(
    name = nameList.first { it.language.name == languageId }.name,
    type = type.name,
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)