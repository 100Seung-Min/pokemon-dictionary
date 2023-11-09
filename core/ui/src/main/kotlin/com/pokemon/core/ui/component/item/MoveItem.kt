package com.pokemon.core.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.ui.util.pokemonClickable
import com.pokemon.core.ui.util.toPokemonType

@Composable
fun MoveItem(
    item: DetailMoveEntity,
    clickAction: () -> Unit,
) {
    PokemonText(
        modifier = Modifier
            .pokemonClickable { clickAction() }
            .background(
                item.type.toPokemonType().typeColor,
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 7.dp, vertical = 5.dp), text = item.name
    )
}