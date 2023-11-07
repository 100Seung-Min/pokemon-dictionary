package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.InfoPokemonEntity
import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.remote.response.pokemon.DetailPokemonResponse
import com.pokemon.core.remote.response.pokemon.InfoPokemonResponse
import com.pokemon.core.remote.response.pokemon.PokemonResponse
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

fun PokemonResponse.toEntity() = PokemonEntity(
    id = id,
    profileUrl = profileUrl
)

fun InfoPokemonResponse.toEntity() = InfoPokemonEntity(
    profileUrl = id.toPokemonImageUrl(),
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
    name = nameList.firstOrNull { it.language.name == languageId }?.name ?: name,
    genus = genusList.firstOrNull { it.language.name == languageId }?.genus ?: "",
    flavorList = flavorList.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
)