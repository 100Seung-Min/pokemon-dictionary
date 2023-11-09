package com.pokemon.core.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.ui.util.pokemonClickable
import com.pokemon.core.ui.util.toPokemonType

@Composable
fun AttributeItem(
    typeString: String,
) {
    val type = typeString.toPokemonType()
    PokemonText(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(type.typeColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 45.dp, vertical = 3.dp),
        text = stringResource(id = type.typeId)
    )
}

@Composable
fun AttributeFilterItem(
    typeString: String,
    isSelected: Boolean,
    onClick: (String) -> Unit,
) {
    val type = typeString.toPokemonType()
    PokemonText(
        modifier = Modifier
            .background(
                type.typeColor.copy(alpha = if (isSelected) 1F else 0.2F),
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 5.dp, vertical = 3.dp)
            .pokemonClickable { onClick(typeString) },
        text = stringResource(id = type.typeId),
        textAlign = TextAlign.Center
    )
}