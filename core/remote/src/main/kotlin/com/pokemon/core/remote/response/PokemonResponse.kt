package com.pokemon.core.remote.response

import com.pokemon.core.domain.entity.PokemonEntity

data class PokemonResponse(
    val id: Int,
    val profileUrl: String,
)

fun PagingPokemonResponse.Result.toResponse(): PokemonResponse {
    val id = url.split("/").let { it[it.lastIndex - 1].toInt() }
    return PokemonResponse(
        id = id,
        profileUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    )
}

fun PokemonResponse.toEntity() = PokemonEntity(
    id = id,
    profileUrl = profileUrl
)
