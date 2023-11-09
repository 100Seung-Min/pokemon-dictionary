package com.pokemon.core.ui.component.item

import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.ui.model.QuizModel
import com.pokemon.core.ui.util.pokemonClickable

@Composable
fun QuizItem(
    item: QuizModel,
    answerId: Int,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onClick: (Boolean) -> Unit,
) {
    var backgroundColor: Boolean? by remember { mutableStateOf(null) }
    PokemonText(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .background(
                if (backgroundColor == null) Color.LightGray else if (backgroundColor == true) Color.Green else Color.Red,
                RoundedCornerShape(20.dp)
            )
            .padding(vertical = 10.dp)
            .pokemonClickable {
                onSelected()
                if (item.name.isNotEmpty() && !isSelected) {
                    backgroundColor = item.id == answerId
                    Handler().postDelayed({
                        onClick(item.id == answerId)
                    }, 1000)
                }
            },
        text = item.name,
        textAlign = TextAlign.Center
    )
}