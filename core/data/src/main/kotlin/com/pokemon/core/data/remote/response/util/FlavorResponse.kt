package com.pokemon.core.data.remote.response.util

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

fun List<FlavorResponse>.toFlavorList(languageId: String): List<String> =
    this.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()

fun List<FlavorTextResponse>.toFlavorTextList(languageId: String): List<String> =
    this.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()
