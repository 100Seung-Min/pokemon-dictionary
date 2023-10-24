package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.entity.InfoEvolutionEntity
import com.pokemon.core.domain.entity.PokemonEntity

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
    var id = chain.species.profileUrl.split("/").dropLast(1).last().toInt()
    val evolutionList =
        mutableListOf(
            listOf(
                PokemonEntity(
                    id = id,
                    profileUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
                )
            )
        )
    var nextEvolution = chain.nextEvolution
    while (true) {
        val sameEvolutionList: MutableList<PokemonEntity> = mutableListOf()
        nextEvolution.forEach {
            id = it.species.profileUrl.split("/").dropLast(1).last().toInt()
            sameEvolutionList.add(PokemonEntity(id = id, profileUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"))
        }
        nextEvolution = nextEvolution.getOrNull(0)?.nextEvolution ?: break
        evolutionList.add(sameEvolutionList)
    }
    return InfoEvolutionEntity(evolutionList = evolutionList)
}