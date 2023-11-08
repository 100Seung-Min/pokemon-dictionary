package com.pokemon.core.data.remote.response.pokemon

import com.google.gson.annotations.SerializedName
import com.pokemon.core.data.remote.response.util.FlavorTextResponse
import com.pokemon.core.data.remote.response.util.GenusResponse
import com.pokemon.core.data.remote.response.util.NameResponse
import com.pokemon.core.data.remote.response.util.URLResponse
import com.pokemon.core.data.remote.response.util.getGenus
import com.pokemon.core.data.remote.response.util.getName
import com.pokemon.core.data.remote.response.util.toFlavorTextList
import com.pokemon.core.data.remote.util.getId
import com.pokemon.core.domain.entity.DetailPokemonEntity

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
    name = nameList.getName(languageId = languageId, defaultValue = name),
    genus = genusList.getGenus(languageId = languageId, defaultValue = ""),
    flavorList = flavorList.toFlavorTextList(languageId = languageId)
)