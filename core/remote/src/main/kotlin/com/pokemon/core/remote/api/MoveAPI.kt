package com.pokemon.core.remote.api

import com.pokemon.core.data.remote.response.move.DetailMoveResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoveAPI {
    @GET("move/{moveId}")
    suspend fun getMoveDetail(
        @Path("moveId") moveId: Int,
    ): DetailMoveResponse
}