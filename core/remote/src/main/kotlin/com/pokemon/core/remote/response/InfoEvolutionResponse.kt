package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.entity.PokemonEntity
import com.pokemon.core.remote.util.getId
import com.pokemon.core.remote.util.toPokemonImageUrl

data class InfoEvolutionResponse(
    @SerializedName("chain")
    val chain: Chain,
) {
    data class Chain(
        @SerializedName("species")
        val species: Species,
        @SerializedName("evolves_to")
        val nextEvolution: List<Chain>,
    ) {
        data class Species(
            @SerializedName("url")
            val profileUrl: String,
        )
    }
}

fun InfoEvolutionResponse.toEntity(): InfoEvolutionEntity {
    var id = chain.species.profileUrl.getId()
    val evolutionList =
        mutableListOf(listOf(PokemonEntity(id = id, profileUrl = id.toPokemonImageUrl())))
    var nextEvolution = chain.nextEvolution
    while (true) {
        val sameEvolutionList: MutableList<PokemonEntity> = mutableListOf()
        nextEvolution.forEach {
            id = it.species.profileUrl.getId()
            sameEvolutionList.add(PokemonEntity(id = id, profileUrl = id.toPokemonImageUrl()))
        }
        nextEvolution = nextEvolution.getOrNull(0)?.nextEvolution ?: break
        evolutionList.add(sameEvolutionList)
    }
    return InfoEvolutionEntity(evolutionList = evolutionList)
}