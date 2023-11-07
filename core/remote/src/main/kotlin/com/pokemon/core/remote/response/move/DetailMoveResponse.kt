package com.pokemon.core.remote.response.move

import com.google.gson.annotations.SerializedName
import com.pokemon.core.remote.response.util.FlavorTextResponse
import com.pokemon.core.remote.response.util.NameResponse
import com.pokemon.core.remote.response.util.TypeResponse

data class DetailMoveResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("flavor_text_entries")
    val flavorList: List<FlavorTextResponse>,
    @SerializedName("type")
    val type: TypeResponse,
    @SerializedName("names")
    val nameList: List<NameResponse>,
)