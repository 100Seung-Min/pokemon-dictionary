package com.pokemon.core.remote.response.util

import com.google.gson.annotations.SerializedName

data class FlavorResponse(
    @SerializedName("text")
    val flavorText: String,
    @SerializedName("language")
    val language: LanguageResponse,
)

data class FlavorTextResponse(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: LanguageResponse,
)
