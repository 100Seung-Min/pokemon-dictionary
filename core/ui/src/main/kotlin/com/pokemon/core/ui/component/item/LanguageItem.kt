package com.pokemon.core.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.util.Language
import com.pokemon.core.design_system.util.changeLanguage
import com.pokemon.core.ui.util.pokemonClickable

@Composable
fun LanguageItem(
    item: Language,
    index: Int,
    isLast: Boolean,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    PokemonText(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .background(
                Color.LightGray,
                if (index == 0) RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                else if (isLast) RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                else RoundedCornerShape(0.dp)
            )
            .padding(vertical = 10.dp)
            .pokemonClickable {
                item.changeLanguage(context = context)
                onClick()
            },
        text = stringResource(id = item.stringId),
        textAlign = TextAlign.Center
    )
    if (!isLast) {
        Divider(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}