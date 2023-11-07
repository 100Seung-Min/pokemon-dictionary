package com.pokemon.core.remote.response.util

import com.google.gson.annotations.SerializedName

data class URLResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)
