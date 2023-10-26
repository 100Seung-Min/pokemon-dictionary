package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.DetailMoveEntity

data class DetailMoveResponse(
    @SerializedName("flavor_text_entries")
    val flavorList: List<Flavor>,
    @SerializedName("type")
    val type: Type,
    @SerializedName("names")
    val nameList: List<Name>,
) {
    data class Flavor(
        @SerializedName("flavor_text")
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

    data class Type(
        @SerializedName("name")
        val name: String,
    )
}

fun DetailMoveResponse.toEntity(languageId: String) = DetailMoveEntity(
    name = nameList.first { it.language.name == languageId }.name,
    type = type.name,
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)