package com.pokemon.core.data.remote.response.util

import com.google.gson.annotations.SerializedName

data class GenusResponse(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: LanguageResponse,
)

fun List<GenusResponse>.getGenus(languageId: String, defaultValue: String): String =
    this.firstOrNull { it.language.name == languageId }?.genus ?: defaultValue