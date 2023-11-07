package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.DetailItemEntity
import com.pokemon.core.remote.response.util.FlavorResponse
import com.pokemon.core.remote.response.util.NameResponse

data class DetailItemResponse(
    @SerializedName("flavor_text_entries")
    val flavorList: List<FlavorResponse>,
    @SerializedName("names")
    val nameList: List<NameResponse>,
)

fun DetailItemResponse.toEntity(languageId: String) = DetailItemEntity(
    name = nameList.first { it.language.name == languageId }.name,
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)