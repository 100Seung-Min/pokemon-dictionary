package com.pokemon.core.data.mapper.remote

import com.pokemon.core.data.mapper.util.getId
import com.pokemon.core.domain.entity.GenerationEntity
import com.pokemon.core.remote.response.util.URLResponse

fun URLResponse.toGenerationEntity() = GenerationEntity(id = url.getId())