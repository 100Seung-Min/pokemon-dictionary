package com.pokemon.core.design_system.attribute

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle

@Immutable
data class PokemonTypography(
    val default: TextStyle,
)

internal val LocalPokemonTypography = compositionLocalOf {
    PokemonTypography(
        default = TextStyle.Default
    )
}

val pokemonTypography = PokemonTypography(
    default = TextStyle.Default
)