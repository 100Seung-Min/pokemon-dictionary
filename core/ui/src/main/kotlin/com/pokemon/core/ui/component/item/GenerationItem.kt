package com.pokemon.core.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.ui.util.pokemonClickable

@Composable
fun GenerationItem(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    PokemonText(
        modifier = Modifier
            .background(
                color = if (isSelected) Color.Cyan else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = PokemonTheme.colors.text,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 3.dp, horizontal = 5.dp)
            .pokemonClickable { onClick() },
        text = name,
        textAlign = TextAlign.Center
    )
}