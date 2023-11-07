package com.pokemon.core.data.mapper.remote

import com.pokemon.core.data.mapper.util.getId
import com.pokemon.core.domain.entity.DetailGenerationEntity
import com.pokemon.core.domain.entity.GenerationEntity
import com.pokemon.core.remote.response.generation.DetailGenerationResponse
import com.pokemon.core.remote.response.util.URLResponse

fun URLResponse.toGenerationEntity() = GenerationEntity(id = url.getId())

fun DetailGenerationResponse.toEntity(languageId: String) = DetailGenerationEntity(
    name = nameList.getName(languageId = languageId, defaultValue = name),
    pokemonList = pokemonList.map { it.toPokemonEntity() }
)