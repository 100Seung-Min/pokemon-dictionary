package com.pokemon.core.data.mapper.remote

import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.remote.response.evolution.InfoEvolutionResponse
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

fun InfoEvolutionResponse.toEntity(): InfoEvolutionEntity {
    var id = chain.species.url.getId()
    val evolutionList =
        mutableListOf(listOf(PokemonEntity(id = id, profileUrl = id.toPokemonImageUrl())))
    var nextEvolution = chain.nextEvolution
    while (true) {
        val sameEvolutionList: MutableList<PokemonEntity> = mutableListOf()
        nextEvolution.forEach {
            id = it.species.url.getId()
            sameEvolutionList.add(PokemonEntity(id = id, profileUrl = id.toPokemonImageUrl()))
        }
        nextEvolution = nextEvolution.getOrNull(0)?.nextEvolution ?: break
        evolutionList.add(sameEvolutionList)
    }
    return InfoEvolutionEntity(evolutionList = evolutionList)
}