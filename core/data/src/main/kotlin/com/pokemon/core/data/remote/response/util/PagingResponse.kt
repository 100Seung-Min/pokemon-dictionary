package com.pokemon.core.data.remote.response.util

import com.google.gson.annotations.SerializedName

data class PagingResponse(
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val result: List<URLResponse>,
)