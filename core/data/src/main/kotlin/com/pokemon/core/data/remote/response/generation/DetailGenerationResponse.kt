package com.pokemon.core.data.remote.response.generation

import com.google.gson.annotations.SerializedName
import com.pokemon.core.data.remote.response.util.NameResponse
import com.pokemon.core.data.remote.response.util.URLResponse
import com.pokemon.core.data.remote.response.util.getName
import com.pokemon.core.data.remote.response.util.toPokemonEntity
import com.pokemon.core.domain.entity.DetailGenerationEntity

data class DetailGenerationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val nameList: List<NameResponse>,
    @SerializedName("pokemon_species")
    val pokemonList: List<URLResponse>,
)

fun DetailGenerationResponse.toEntity(languageId: String) = DetailGenerationEntity(
    name = nameList.getName(languageId = languageId, defaultValue = name),
    pokemonList = pokemonList.map { it.toPokemonEntity() }
)
