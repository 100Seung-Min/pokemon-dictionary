package com.pokemon.feature.setting.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.core.domain.usecase.system.FetchIsDarkThemeUseCase
import com.pokemon.core.domain.usecase.system.SaveIsDarkThemeUseCase
import com.pokemon.core.domain.usecase.system.SaveLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val fetchIsDarkThemeUseCase: FetchIsDarkThemeUseCase,
    private val saveIsDarkThemeUseCase: SaveIsDarkThemeUseCase,
) : ViewModel(), ContainerHost<SettingState, Unit> {
    override val container = container<SettingState, Unit>(SettingState())

    init {
        fetchIsDarkTheme()
    }

    private fun fetchIsDarkTheme() = intent {
        fetchIsDarkThemeUseCase().onSuccess {
            reduce { state.copy(isDarkTheme = it) }
        }
    }

    fun saveLanguage(language: String) = viewModelScope.launch {
        saveLanguageUseCase(language = language)
    }

    fun saveIsDarkTheme(isDarkTheme: Boolean) = intent {
        saveIsDarkThemeUseCase(isDarkTheme = isDarkTheme).onSuccess {
            reduce { state.copy(isDarkTheme = !state.isDarkTheme) }
        }
    }
}