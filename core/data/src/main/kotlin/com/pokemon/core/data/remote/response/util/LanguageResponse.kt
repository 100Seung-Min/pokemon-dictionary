package com.pokemon.core.data.remote.response.util

import com.google.gson.annotations.SerializedName

data class LanguageResponse(
    @SerializedName("name")
    val name: String,
)