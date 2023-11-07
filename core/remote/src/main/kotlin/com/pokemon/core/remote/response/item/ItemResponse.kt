package com.pokemon.core.remote.response.item

import com.pokemon.core.remote.response.util.URLResponse

data class ItemResponse(
    val url: String,
    val name: String,
)

fun URLResponse.toItemResponse() = ItemResponse(
    url = url,
    name = name
)