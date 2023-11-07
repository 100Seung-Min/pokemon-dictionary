package com.pokemon.core.remote.response.item

import com.pokemon.core.remote.response.util.URLResponse
import com.pokemon.core.remote.util.getId

data class ItemResponse(
    val id: Int,
    val name: String,
)

fun URLResponse.toItemResponse() = ItemResponse(
    id = url.getId(),
    name = name
)