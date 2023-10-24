package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.InfoPokemonEntity
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
    val species: Species,
) {
    data class Types(
        @SerializedName("type")
        val type: Type,
    ) {
        data class Type(
            @SerializedName("name")
            val name: String,
        )
    }

    data class Moves(
        @SerializedName("move")
        val move: Move,
    ) {
        data class Move(
            @SerializedName("url")
            val url: String,
        )
    }

    data class Species(
        @SerializedName("url")
        val url: String,
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