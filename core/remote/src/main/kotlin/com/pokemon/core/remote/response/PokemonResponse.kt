package com.pokemon.core.remote.response

import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

data class PokemonResponse(
    val id: Int,
    val profileUrl: String,
)

fun PagingPokemonResponse.Result.toResponse(): PokemonResponse {
    val id = url.getId()
    return PokemonResponse(
        id = id,
        profileUrl = id.toPokemonImageUrl()
    )
}

fun PokemonResponse.toEntity() = PokemonEntity(
    id = id,
    profileUrl = profileUrl
)
