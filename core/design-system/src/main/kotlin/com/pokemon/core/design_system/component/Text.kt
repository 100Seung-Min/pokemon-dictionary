package com.pokemon.core.design_system.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.pokemon.core.design_system.PokemonTheme

@Composable
fun PokemonText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
) {
    Text(modifier = modifier, text = text, color = PokemonTheme.colors.text, textAlign = textAlign)
}