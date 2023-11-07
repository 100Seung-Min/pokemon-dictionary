package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.remote.response.util.URLResponse
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

data class InfoEvolutionResponse(
    @SerializedName("chain")
    val chain: Chain,
) {
    data class Chain(
        @SerializedName("species")
        val species: URLResponse,
        @SerializedName("evolves_to")
        val nextEvolution: List<Chain>,
    )
}

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