package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

data class DetailPokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChain,
    @SerializedName("flavor_text_entries")
    val flavorList: List<Flavor>,
    @SerializedName("genera")
    val genusList: List<Genus>,
    @SerializedName("names")
    val nameList: List<Name>,
) {
    data class EvolutionChain(
        @SerializedName("url")
        val url: String,
    )

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
    englishName = name,
    evolutionId = evolutionChain.url.getId(),
    profileUrl = id.toPokemonImageUrl(),
    name = nameList.first { it.language.name == "ko" }.name,
    genus = genusList.first { it.language.name == "ko" }.genus,
    flavorList = flavorList.filter { it.language.name == "ko" }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)