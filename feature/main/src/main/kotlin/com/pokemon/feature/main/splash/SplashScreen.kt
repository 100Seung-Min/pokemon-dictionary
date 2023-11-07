package com.pokemon.feature.main.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.R
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.navigation.home.MainNavigationItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    changeDarkTheme: (Boolean) -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val container = splashViewModel.container
    val state = container.stateFlow.collectAsState().value
    val context = LocalContext.current
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash_animation)
    )
    val lottieAnimation = rememberLottieAnimatable()

    LaunchedEffect(Unit) {
        splashViewModel.settingLanguage(context)
    }

    LaunchedEffect(state.isDarkTheme) {
        changeDarkTheme(state.isDarkTheme)
        delay(2000)
        navController.navigate(MainNavigationItem.Home.route) {
            popUpTo(MainNavigationItem.Splash.route) {
                inclusive = true
            }
        }
    }

    LaunchedEffect(composition) {
        lottieAnimation.animate(
            composition = composition,
            initialProgress = 0f
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PokemonTheme.colors.main),
    ) {
        LottieAnimation(
            modifier = Modifier
                .align(Alignment.Center)
                .size(300.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}