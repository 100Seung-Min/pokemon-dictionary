package com.pokemon.core.remote.util

fun String.getId() = split("/").dropLast(1).last().toInt()

fun Int.toPokemonImageUrl() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$this.png"