package com.pokemon.core.design_system.attribute

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PokemonColor(
    val main: Color,
)

internal val LocalPokemonColors = staticCompositionLocalOf {
    PokemonColor(
        main = Color.Unspecified
    )
}

val lightColor = PokemonColor(
    main = Color(0xFFFFFFFF)
)

val darkColor = PokemonColor(
    main = Color(0xFFFFFFFF)
)