package com.pokemon.core.data.remote.response.util

import com.google.gson.annotations.SerializedName
import com.pokemon.core.data.remote.util.getId
import com.pokemon.core.data.remote.util.toPokemonImageUrl
import com.pokemon.core.domain.entity.GenerationEntity
import com.pokemon.core.domain.entity.ItemEntity
import com.pokemon.core.domain.entity.PokemonEntity

data class URLResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)

fun URLResponse.toGenerationEntity() = GenerationEntity(id = url.getId())

fun URLResponse.toItemEntity() = ItemEntity(id = url.getId())

fun URLResponse.toPokemonEntity(): PokemonEntity {
    val id = url.getId()
    return PokemonEntity(
        id = id,
        profileUrl = id.toPokemonImageUrl()
    )
}