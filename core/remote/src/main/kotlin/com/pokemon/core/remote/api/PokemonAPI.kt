package com.pokemon.core.remote.api

import com.pokemon.core.remote.response.PagingPokemonResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = PAGING_SIZE,
    ): PagingPokemonResponse
}