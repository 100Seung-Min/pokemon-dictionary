package com.pokemon.core.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.R

data class PokemonType(
    val typeId: Int,
    val typeColor: Color,
)

@Composable
fun String.toPokemonType() = when (this) {
    "normal" -> PokemonType(R.string.normal, PokemonTheme.colors.normal)
    "fighting" -> PokemonType(R.string.fighting, PokemonTheme.colors.fighting)
    "flying" -> PokemonType(R.string.flying, PokemonTheme.colors.flying)
    "poison" -> PokemonType(R.string.poison, PokemonTheme.colors.poison)
    "ground" -> PokemonType(R.string.ground, PokemonTheme.colors.ground)
    "rock" -> PokemonType(R.string.rock, PokemonTheme.colors.rock)
    "bug" -> PokemonType(R.string.bug, PokemonTheme.colors.bug)
    "ghost" -> PokemonType(R.string.ghost, PokemonTheme.colors.ghost)
    "steel" -> PokemonType(R.string.steel, PokemonTheme.colors.steel)
    "fire" -> PokemonType(R.string.fire, PokemonTheme.colors.fire)
    "water" -> PokemonType(R.string.water, PokemonTheme.colors.water)
    "grass" -> PokemonType(R.string.grass, PokemonTheme.colors.grass)
    "electric" -> PokemonType(R.string.electric, PokemonTheme.colors.electric)
    "psychic" -> PokemonType(R.string.psychic, PokemonTheme.colors.psychic)
    "ice" -> PokemonType(R.string.ice, PokemonTheme.colors.ice)
    "dragon" -> PokemonType(R.string.dragon, PokemonTheme.colors.dragon)
    "dark" -> PokemonType(R.string.dark, PokemonTheme.colors.dark)
    "fairy" -> PokemonType(R.string.fairy, PokemonTheme.colors.fairy)
    "unknown" -> PokemonType(R.string.unknown, PokemonTheme.colors.unknown)
    "shadow" -> PokemonType(R.string.shadow, PokemonTheme.colors.shadow)
    else -> PokemonType(R.string.normal, PokemonTheme.colors.normal)
}