package com.pokemon.core.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pokemon.core.design_system.attribute.PokemonIcon
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.ui.model.AcademyMenuModel
import com.pokemon.core.ui.util.pokemonClickable

@Composable
fun AcademyMenuItem(
    item: AcademyMenuModel,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .background(item.backgroundColor, RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .pokemonClickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PokemonText(text = item.title)
        PokemonIcon(modifier = Modifier.size(130.dp), icon = item.icon)
    }
}