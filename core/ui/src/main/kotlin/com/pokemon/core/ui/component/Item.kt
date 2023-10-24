package com.pokemon.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.ui.util.toPokemonType

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
            .background(backgroundColor ?: PokemonTheme.colors.main)
            .padding(10.dp)
            .clickable { clickAction() }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
        )
        Text(text = name ?: "")
    }
}

@Composable
fun AttributeItem(
    typeString: String,
) {
    val type = typeString.toPokemonType()
    Text(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(type.typeColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 45.dp, vertical = 3.dp),
        text = stringResource(id = type.typeId)
    )
}

@Composable
fun InfoItem(
    title: String,
    content: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = content)
    }
}

@Composable
fun MoveItem(
    item: DetailMoveEntity,
    clickAction: () -> Unit,
) {
    Text(
        modifier = Modifier
            .clickable { clickAction() }
            .background(
                item.type.toPokemonType().typeColor,
                RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 7.dp, vertical = 5.dp), text = item.name
    )
}