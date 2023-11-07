package com.pokemon.core.remote.response.pokemon

import com.google.gson.annotations.SerializedName
import com.pokemon.core.remote.response.util.TypeResponse
import com.pokemon.core.remote.response.util.URLResponse

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