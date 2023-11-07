package com.pokemon.core.remote.response.pokemon

import com.google.gson.annotations.SerializedName
import com.pokemon.core.remote.response.util.TypeResponse
import com.pokemon.core.remote.response.util.URLResponse

data class InfoPokemonResponse(
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
    @SerializedName("sprites")
    val sprites: Sprites,
) {
    data class Types(
        @SerializedName("type")
        val type: TypeResponse,
    )

    data class Moves(
        @SerializedName("move")
        val move: URLResponse,
    )

    data class Sprites(
        @SerializedName("other")
        val other: Other,
    ) {
        data class Other(
            @SerializedName("official-artwork")
            val officialArtwork: OfficialArtwork,
        ) {
            data class OfficialArtwork(
                @SerializedName("front_default")
                val imageUrl: String?,
            )
        }
    }
}