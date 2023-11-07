package com.pokemon.feature.main.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.core.design_system.util.Language
import com.pokemon.core.design_system.util.changeLanguage
import com.pokemon.core.domain.usecase.system.FetchIsDarkThemeUseCase
import com.pokemon.core.domain.usecase.system.FetchLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val fetchLanguageUseCase: FetchLanguageUseCase,
    private val fetchIsDarkThemeUseCase: FetchIsDarkThemeUseCase,
) : ViewModel(), ContainerHost<SplashState, Unit> {
    override val container = container<SplashState, Unit>(SplashState())

    fun settingLanguage(context: Context) = intent {
        viewModelScope.launch {
            fetchLanguageUseCase().onSuccess {
                Language.values().toList().forEach { language ->
                    if (language.type == it) language.changeLanguage(context = context)
                }
            }
        }
        fetchIsDarkTheme()
    }

    private fun fetchIsDarkTheme() = intent {
        fetchIsDarkThemeUseCase().onSuccess {
            reduce { state.copy(isDarkTheme = it) }
        }
    }
}