package com.pokemon.core.remote.response.pokemon

import com.pokemon.core.remote.response.util.URLResponse
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

data class PokemonResponse(
    val id: Int,
    val profileUrl: String,
)

fun URLResponse.toPokemonResponse(): PokemonResponse {
    val id = url.getId()
    return PokemonResponse(
        id = id,
        profileUrl = id.toPokemonImageUrl()
    )
}