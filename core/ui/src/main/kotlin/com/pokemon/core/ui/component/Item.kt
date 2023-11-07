package com.pokemon.core.ui.component

import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.attribute.PokemonIcon
import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.util.Language
import com.pokemon.core.design_system.util.changeLanguage
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.ui.model.AcademyMenuModel
import com.pokemon.core.ui.model.QuizModel
import com.pokemon.core.ui.util.pokemonClickable
import com.pokemon.core.ui.util.toPokemonType
import kotlinx.coroutines.delay

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
            .pokemonClickable { clickAction() }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
        )
        PokemonText(text = name ?: "")
    }
}

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

@Composable
fun GenerationItem(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Text(
        modifier = Modifier
            .background(
                color = if (isSelected) Color.Cyan else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 3.dp, horizontal = 5.dp)
            .pokemonClickable { onClick() },
        text = name,
        fontSize = 12.sp,
        textAlign = TextAlign.Center
    )
}