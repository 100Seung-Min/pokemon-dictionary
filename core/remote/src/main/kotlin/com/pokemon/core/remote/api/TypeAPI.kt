package com.pokemon.core.remote.api

import com.pokemon.core.data.remote.response.type.DetailTypeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeAPI {
    @GET("type/{typeId}")
    suspend fun getTypeDetail(
        @Path("typeId") typeId: Int,
    ): DetailTypeResponse
}