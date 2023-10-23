package com.pokemon.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.pokemon.core.design_system.attribute.LocalPokemonColors
import com.pokemon.core.design_system.attribute.LocalPokemonTypography
import com.pokemon.core.design_system.attribute.PokemonColor
import com.pokemon.core.design_system.attribute.PokemonTypography
import com.pokemon.core.design_system.attribute.darkColor
import com.pokemon.core.design_system.attribute.lightColor
import com.pokemon.core.design_system.attribute.pokemonTypography

@Composable
fun PokemonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) darkColor else lightColor

    CompositionLocalProvider(
        LocalPokemonColors provides colors,
        LocalPokemonTypography provides pokemonTypography
    ) {
        MaterialTheme(
            content = {
                Box(
                    modifier = Modifier
                        .background(PokemonTheme.colors.main)
                        .fillMaxSize()
                ) {
                    content()
                }
            }
        )
    }
}

object PokemonTheme {
    val colors: PokemonColor
        @Composable
        get() = LocalPokemonColors.current
    val typography: PokemonTypography
        @Composable
        get() = LocalPokemonTypography.current
}