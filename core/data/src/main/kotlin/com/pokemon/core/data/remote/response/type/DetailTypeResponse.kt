package com.pokemon.core.data.remote.response.type

import com.google.gson.annotations.SerializedName
import com.pokemon.core.data.remote.response.util.URLResponse
import com.pokemon.core.data.remote.response.util.toPokemonEntity
import com.pokemon.core.domain.entity.DetailTypeEntity

data class DetailTypeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("pokemon")
    val pokemonList: List<Pokemon>,
) {
    data class Pokemon(
        @SerializedName("pokemon")
        val pokemon: URLResponse,
    )
}

fun DetailTypeResponse.toEntity() = DetailTypeEntity(
    id = id,
    name = name,
    pokemonList = pokemonList.map { it.pokemon.toPokemonEntity() }.filter { it.id <= 1017 }
)
