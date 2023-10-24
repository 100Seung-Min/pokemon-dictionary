package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.InfoPokemonEntity
import com.pokemon.core.remote.util.getId

data class InfoPokemonResponse(
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("types")
    val typeList: List<Types>,
    @SerializedName("moves")
    val moveList: List<Moves>,
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
}

fun InfoPokemonResponse.toEntity() = InfoPokemonEntity(
    weight = weight,
    height = height,
    typeList = typeList.map { it.type.name },
    moveList = moveList.map { it.move.url.getId() }
)