package com.pokemon.core.data.mapper.remote

import com.pokemon.core.remote.response.util.FlavorResponse
import com.pokemon.core.remote.response.util.FlavorTextResponse
import com.pokemon.core.remote.response.util.GenusResponse
import com.pokemon.core.remote.response.util.NameResponse

fun List<NameResponse>.getName(languageId: String, defaultValue: String): String =
    this.firstOrNull { it.language.name == languageId }?.name ?: defaultValue

fun List<GenusResponse>.getGenus(languageId: String, defaultValue: String): String =
    this.firstOrNull { it.language.name == languageId }?.genus ?: defaultValue

fun List<FlavorResponse>.toFlavorList(languageId: String): List<String> =
    this.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()

fun List<FlavorTextResponse>.toFlavorTextList(languageId: String): List<String> =
    this.filter { it.language.name == languageId }
        .map { it.flavorText.replace("\n", " ") }.distinct()