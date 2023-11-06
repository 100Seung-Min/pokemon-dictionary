package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName

data class PagingItemResponse(
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val result: List<Result>,
) {
    data class Result(
        @SerializedName("url")
        val url: String,
        @SerializedName("name")
        val name: String,
    )
}
