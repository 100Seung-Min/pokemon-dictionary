package com.pokemon.core.remote.response.item

import com.google.gson.annotations.SerializedName
import com.pokemon.core.remote.response.util.FlavorResponse
import com.pokemon.core.remote.response.util.NameResponse

data class DetailItemResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("flavor_text_entries")
    val flavorList: List<FlavorResponse>,
    @SerializedName("names")
    val nameList: List<NameResponse>,
)