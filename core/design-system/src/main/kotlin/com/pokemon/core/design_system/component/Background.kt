package com.pokemon.core.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.R

@Composable
fun PokemonBackground(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_animation)
    )
    val lottieAnimation = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimation.animate(
            composition = composition,
            initialProgress = 0f
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PokemonTheme.colors.main),
        content = {
            if (isLoading) {
                LottieAnimation(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(200.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                )
            } else {
                Column {
                    content()
                }
            }
        }
    )
}