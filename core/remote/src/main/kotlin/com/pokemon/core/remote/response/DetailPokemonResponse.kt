package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.DetailPokemonEntity

data class DetailPokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("flavor_text_entries")
    val flavorList: List<Flavor>,
    @SerializedName("genera")
    val genusList: List<Genus>,
    @SerializedName("names")
    val nameList: List<Name>,
) {
    data class Flavor(
        @SerializedName("flavor_text")
        val flavorText: String,
        @SerializedName("language")
        val language: LanguageResponse,
    )

    data class Genus(
        @SerializedName("genus")
        val genus: String,
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

fun DetailPokemonResponse.toEntity() = DetailPokemonEntity(
    id = id,
    profileUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
    name = nameList.first { it.language.name == "ko" }.name,
    genus = genusList.first { it.language.name == "ko" }.genus,
    flavorList = flavorList.filter { it.language.name == "ko" }.map { it.flavorText.replace("\n", " ") }.distinct()
)
