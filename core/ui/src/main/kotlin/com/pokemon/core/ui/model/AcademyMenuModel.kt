package com.pokemon.core.ui.model

import androidx.compose.ui.graphics.Color
import com.pokemon.core.design_system.attribute.PokemonIconList

data class AcademyMenuModel(
    val title: String,
    val icon: PokemonIconList,
    val backgroundColor: Color,
    val route: String,
)