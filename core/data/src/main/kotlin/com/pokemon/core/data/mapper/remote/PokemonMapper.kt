package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.InfoPokemonEntity
import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.remote.response.pokemon.DetailPokemonResponse
import com.pokemon.core.remote.response.pokemon.InfoPokemonResponse
import com.pokemon.core.data.mapper.util.getId
import com.pokemon.core.data.mapper.util.toPokemonImageUrl
import com.pokemon.core.remote.response.util.URLResponse

fun URLResponse.toPokemonEntity(): PokemonEntity {
    val id = url.getId()
    return PokemonEntity(
        id = id,
        profileUrl = id.toPokemonImageUrl()
    )
}

fun InfoPokemonResponse.toEntity() = InfoPokemonEntity(
    profileUrl = sprites.other.officialArtwork.imageUrl ?: "",
    speciesId = species.url.getId(),
    weight = weight,
    height = height,
    typeList = typeList.map { it.type.name },
    moveList = moveList.map { it.move.url.getId() }
)

fun DetailPokemonResponse.toEntity(languageId: String) = DetailPokemonEntity(
    id = id,
    englishName = name,
    evolutionId = evolutionChain.url.getId(),
    name = nameList.getName(languageId = languageId, defaultValue = name),
    genus = genusList.getGenus(languageId = languageId, defaultValue = ""),
    flavorList = flavorList.toFlavorTextList(languageId = languageId)
)