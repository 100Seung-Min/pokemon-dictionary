package com.pokemon.core.remote.api

import com.pokemon.core.remote.response.item.DetailItemResponse
import com.pokemon.core.remote.response.util.PagingResponse
import com.pokemon.core.remote.util.PAGING_SIZE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemAPI {
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