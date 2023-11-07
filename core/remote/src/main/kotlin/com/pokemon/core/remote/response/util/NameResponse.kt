package com.pokemon.core.remote.response.util

import com.google.gson.annotations.SerializedName

data class NameResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("language")
    val language: LanguageResponse,
)