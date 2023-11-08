package com.pokemon.core.data.remote.response.item

import com.google.gson.annotations.SerializedName
import com.pokemon.core.data.remote.response.util.FlavorResponse
import com.pokemon.core.data.remote.response.util.NameResponse
import com.pokemon.core.data.remote.response.util.getName
import com.pokemon.core.data.remote.response.util.toFlavorList
import com.pokemon.core.domain.entity.DetailItemEntity

data class DetailItemResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("flavor_text_entries")
    val flavorList: List<FlavorResponse>,
    @SerializedName("names")
    val nameList: List<NameResponse>,
    @SerializedName("sprites")
    val sprites: Sprites,
) {
    data class Sprites(
        @SerializedName("default")
        val imageUrl: String?,
    )
}

fun DetailItemResponse.toEntity(languageId: String) = DetailItemEntity(
    name = nameList.getName(languageId = languageId, defaultValue = name),
    flavorList = flavorList.toFlavorList(languageId = languageId),
    imageUrl = sprites.imageUrl ?: ""
)