package com.pokemon.core.data.remote.response.util

import com.google.gson.annotations.SerializedName

data class TypeResponse(
    @SerializedName("name")
    val name: String,
)