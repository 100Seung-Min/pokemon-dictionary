package com.pokemon.core.remote.response

import com.google.gson.annotations.SerializedName

data class LanguageResponse(
    @SerializedName("name")
    val name: String,
)