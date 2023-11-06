package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.DetailItemEntity

data class DetailItemResponse(
    @SerializedName("flavor_text_entries")
    val flavorList: List<Flavor>,
    @SerializedName("names")
    val nameList: List<Name>,
) {
    data class Flavor(
        @SerializedName("text")
        val flavorText: String,
        @SerializedName("language")
        val language: LanguageResponse,
    )

    data class Name(
        @SerializedName("name")
        val name: String,
        @SerializedName("language")
        val language: LanguageResponse,
    )
}

fun DetailItemResponse.toEntity(languageId: String) = DetailItemEntity(
    name = nameList.first { it.language.name == languageId }.name,
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)