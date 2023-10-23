package com.pokemon.core.design_system.attribute

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PokemonColor(
    val main: Color,
    val normal: Color,
    val fighting: Color,
    val flying: Color,
    val poison: Color,
    val ground: Color,
    val rock: Color,
    val bug: Color,
    val ghost: Color,
    val steel: Color,
    val fire: Color,
    val water: Color,
    val grass: Color,
    val electric: Color,
    val psychic: Color,
    val ice: Color,
    val dragon: Color,
    val dark: Color,
    val fairy: Color,
    val unknown: Color,
    val shadow: Color,
)

internal val LocalPokemonColors = staticCompositionLocalOf {
    PokemonColor(
        main = Color.Unspecified,
        normal = Color.Unspecified,
        fighting = Color.Unspecified,
        flying = Color.Unspecified,
        poison = Color.Unspecified,
        ground = Color.Unspecified,
        rock = Color.Unspecified,
        bug = Color.Unspecified,
        ghost = Color.Unspecified,
        steel = Color.Unspecified,
        fire = Color.Unspecified,
        water = Color.Unspecified,
        grass = Color.Unspecified,
        electric = Color.Unspecified,
        psychic = Color.Unspecified,
        ice = Color.Unspecified,
        dragon = Color.Unspecified,
        dark = Color.Unspecified,
        fairy = Color.Unspecified,
        unknown = Color.Unspecified,
        shadow = Color.Unspecified,
    )
}

val lightColor = PokemonColor(
    main = Color(0xFFFFFFFF),
    normal = Color(0xFFA8A878),
    fighting = Color(0xFFC03028),
    flying = Color(0xFFA890F0),
    poison = Color(0xFFA040A0),
    ground = Color(0xFFE0C068),
    rock = Color(0xFFB8A038),
    bug = Color(0xFFA8B820),
    ghost = Color(0xFF705898),
    steel = Color(0xFFB8B8D0),
    fire = Color(0xFFF08030),
    water = Color(0xFF6890F0),
    grass = Color(0xFF78C850),
    electric = Color(0xFFF8D030),
    psychic = Color(0xFFF85888),
    ice = Color(0xFF98D8D8),
    dragon = Color(0xFF7038F8),
    dark = Color(0xFF705848),
    fairy = Color(0xFFEE99AC),
    unknown = Color(0xFF68A090),
    shadow = Color(0xFF808080),
)

val darkColor = PokemonColor(
    main = Color(0xFFFFFFFF),
    normal = Color(0xFFA8A878),
    fighting = Color(0xFFC03028),
    flying = Color(0xFFA890F0),
    poison = Color(0xFFA040A0),
    ground = Color(0xFFE0C068),
    rock = Color(0xFFB8A038),
    bug = Color(0xFFA8B820),
    ghost = Color(0xFF705898),
    steel = Color(0xFFB8B8D0),
    fire = Color(0xFFF08030),
    water = Color(0xFF6890F0),
    grass = Color(0xFF78C850),
    electric = Color(0xFFF8D030),
    psychic = Color(0xFFF85888),
    ice = Color(0xFF98D8D8),
    dragon = Color(0xFF7038F8),
    dark = Color(0xFF705848),
    fairy = Color(0xFFEE99AC),
    unknown = Color(0xFF68A090),
    shadow = Color(0xFF808080),
)