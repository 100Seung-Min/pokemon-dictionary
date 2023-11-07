package com.pokemon.core.remote.response.util

import com.google.gson.annotations.SerializedName

data class GenusResponse(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: LanguageResponse,
)
