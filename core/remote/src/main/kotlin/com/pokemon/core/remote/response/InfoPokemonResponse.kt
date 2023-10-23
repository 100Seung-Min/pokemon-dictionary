package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.InfoPokemonEntity

data class InfoPokemonResponse(
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("types")
    val typeList: List<Types>,
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
}

fun InfoPokemonResponse.toEntity() = InfoPokemonEntity(
    weight = weight,
    height = height,
    typeList = typeList.map { it.type.name }
)