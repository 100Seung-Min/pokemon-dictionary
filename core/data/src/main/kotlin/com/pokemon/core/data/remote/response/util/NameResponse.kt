package com.pokemon.core.data.remote.response.util

import com.google.gson.annotations.SerializedName

data class NameResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("language")
    val language: LanguageResponse,
)

fun List<NameResponse>.getName(languageId: String, defaultValue: String): String =
    this.firstOrNull { it.language.name == languageId }?.name ?: defaultValue