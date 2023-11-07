package com.pokemon.feature.main.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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

    LaunchedEffect(Unit) {
        splashViewModel.settingLanguage(context)
    }

    LaunchedEffect(state.isDarkTheme) {
        changeDarkTheme(state.isDarkTheme)
        delay(1000)
        navController.navigate(MainNavigationItem.Home.route) {
            popUpTo(MainNavigationItem.Splash.route) {
                inclusive = true
            }
        }
    }

    PokemonBackground {

    }
}