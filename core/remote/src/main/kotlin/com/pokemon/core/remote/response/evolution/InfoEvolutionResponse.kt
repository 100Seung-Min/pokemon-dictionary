package com.pokemon.core.remote.response.evolution

import com.google.gson.annotations.SerializedName
import com.pokemon.core.remote.response.util.URLResponse

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