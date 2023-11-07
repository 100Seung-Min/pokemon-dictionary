package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.InfoPokemonEntity
import com.pokemon.core.remote.response.util.TypeResponse
import com.pokemon.core.remote.response.util.URLResponse
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

data class InfoPokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("types")
    val typeList: List<Types>,
    @SerializedName("moves")
    val moveList: List<Moves>,
    @SerializedName("species")
    val species: URLResponse,
) {
    data class Types(
        @SerializedName("type")
        val type: TypeResponse,
    )

    data class Moves(
        @SerializedName("move")
        val move: URLResponse,
    )
}

fun InfoPokemonResponse.toEntity() = InfoPokemonEntity(
    profileUrl = id.toPokemonImageUrl(),
    speciesId = species.url.getId(),
    weight = weight,
    height = height,
    typeList = typeList.map { it.type.name },
    moveList = moveList.map { it.move.url.getId() }
)