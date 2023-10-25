package com.pokemon.core.design_system.attribute

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.pokemon.core.design_system.R
import javax.annotation.concurrent.Immutable

@Composable
fun PokemonIcon(
    modifier: Modifier = Modifier,
    icon: PokemonIconList,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = icon.drawableId),
        contentDescription = icon.contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}

@Immutable
class PokemonIconList private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {
        @Stable
        val NextEvolution = PokemonIconList(
            drawableId = R.drawable.ic_next_evolution,
            contentDescription = "nextEvolution"
        )

        @Stable
        val NavHome = PokemonIconList(
            drawableId = R.drawable.ic_home,
            contentDescription = "navHome"
        )

        @Stable
        val NavAcademy = PokemonIconList(
            drawableId = R.drawable.ic_academy,
            contentDescription = "navAcademy"
        )

        @Stable
        val NavSetting = PokemonIconList(
            drawableId = R.drawable.ic_setting,
            contentDescription = "navSetting"
        )
    }
}