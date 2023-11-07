package com.pokemon.core.remote.api

import com.pokemon.core.remote.response.DetailItemResponse
import com.pokemon.core.remote.response.DetailMoveResponse
import com.pokemon.core.remote.response.DetailPokemonResponse
import com.pokemon.core.remote.response.InfoEvolutionResponse
import com.pokemon.core.remote.response.InfoPokemonResponse
import com.pokemon.core.remote.response.util.PagingResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = PAGING_SIZE,
    ): PagingResponse

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonInfo(
        @Path("pokemonId") pokemonId: Int,
    ): InfoPokemonResponse

    @GET("pokemon-species/{pokemonId}")
    suspend fun getPokemonDetail(
        @Path("pokemonId") pokemonId: Int,
    ): DetailPokemonResponse

    @GET("move/{moveId}")
    suspend fun getMoveDetail(
        @Path("moveId") moveId: Int,
    ): DetailMoveResponse

    @GET("evolution-chain/{evolutionId}")
    suspend fun getEvolutionInfo(
        @Path("evolutionId") evolutionId: Int,
    ): InfoEvolutionResponse

    @GET("item")
    suspend fun getItemList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = PAGING_SIZE,
    ): PagingResponse

    @GET("item/{itemId}")
    suspend fun getItemDetail(
        @Path("itemId") itemId: Int,
    ): DetailItemResponse
}