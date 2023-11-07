package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.remote.response.util.FlavorTextResponse
import com.pokemon.core.remote.response.util.GenusResponse
import com.pokemon.core.remote.response.util.NameResponse
import com.pokemon.core.remote.response.util.URLResponse
import com.pokemon.core.remote.util.getId

data class DetailPokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("evolution_chain")
    val evolutionChain: URLResponse,
    @SerializedName("flavor_text_entries")
    val flavorList: List<FlavorTextResponse>,
    @SerializedName("genera")
    val genusList: List<GenusResponse>,
    @SerializedName("names")
    val nameList: List<NameResponse>,
)

fun DetailPokemonResponse.toEntity(languageId: String) = DetailPokemonEntity(
    id = id,
    englishName = name,
    evolutionId = evolutionChain.url.getId(),
    name = nameList.firstOrNull { it.language.name == languageId }?.name ?: name,
    genus = genusList.firstOrNull { it.language.name == languageId }?.genus ?: "",
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)