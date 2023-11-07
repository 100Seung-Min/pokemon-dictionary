package com.pokemon.core.remote.response.generation

import com.google.gson.annotations.SerializedName
import com.pokemon.core.remote.response.util.NameResponse
import com.pokemon.core.remote.response.util.URLResponse

data class DetailGenerationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val nameList: List<NameResponse>,
    @SerializedName("pokemon_species")
    val pokemonList: List<URLResponse>,
)
