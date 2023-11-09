package com.pokemon.core.ui.component.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pokemon.core.design_system.component.PokemonText

@Composable
fun InfoItem(
    title: String,
    content: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonText(text = title)
        Spacer(modifier = Modifier.height(4.dp))
        PokemonText(text = content)
    }
}