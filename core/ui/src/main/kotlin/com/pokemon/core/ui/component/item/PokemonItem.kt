package com.pokemon.core.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pokemon.core.design_system.R
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.ui.util.pokemonClickable

@Composable
fun PokemonItem(
    name: String?,
    imageUrl: String,
    backgroundColor: Color?,
    clickAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor ?: Color.LightGray)
            .padding(10.dp)
            .pokemonClickable { clickAction() }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_pokemon_placeholder),
            error = painterResource(id = R.drawable.ic_pokemon_placeholder)
        )
        PokemonText(text = name ?: "???")
    }
}