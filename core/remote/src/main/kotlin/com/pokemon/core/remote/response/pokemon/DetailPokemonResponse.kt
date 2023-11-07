package com.pokemon.core.remote.response.pokemon

import com.google.gson.annotations.SerializedName
import com.pokemon.core.remote.response.util.FlavorTextResponse
import com.pokemon.core.remote.response.util.GenusResponse
import com.pokemon.core.remote.response.util.NameResponse
import com.pokemon.core.remote.response.util.URLResponse

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