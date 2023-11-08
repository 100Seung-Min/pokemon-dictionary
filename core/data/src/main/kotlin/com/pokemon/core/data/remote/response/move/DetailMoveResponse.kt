package com.pokemon.core.data.remote.response.move

import com.google.gson.annotations.SerializedName
import com.pokemon.core.data.remote.response.util.FlavorTextResponse
import com.pokemon.core.data.remote.response.util.NameResponse
import com.pokemon.core.data.remote.response.util.TypeResponse
import com.pokemon.core.data.remote.response.util.getName
import com.pokemon.core.data.remote.response.util.toFlavorTextList
import com.pokemon.core.domain.entity.DetailMoveEntity

data class DetailMoveResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("flavor_text_entries")
    val flavorList: List<FlavorTextResponse>,
    @SerializedName("type")
    val type: TypeResponse,
    @SerializedName("names")
    val nameList: List<NameResponse>,
)

fun DetailMoveResponse.toEntity(languageId: String) = DetailMoveEntity(
    name = nameList.getName(languageId = languageId, defaultValue = name),
    type = type.name,
    flavorList = flavorList.toFlavorTextList(languageId = languageId)
)