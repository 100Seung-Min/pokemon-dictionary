package com.pokemon.core.remote.response.pokemon

import com.pokemon.core.remote.response.util.URLResponse

data class PokemonResponse(
    val url: String,
)

fun URLResponse.toPokemonResponse() = PokemonResponse(url = url)