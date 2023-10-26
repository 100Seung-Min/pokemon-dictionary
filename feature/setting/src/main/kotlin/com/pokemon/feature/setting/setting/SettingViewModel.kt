package com.pokemon.feature.setting.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.core.domain.usecase.system.SaveLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val saveLanguageUseCase: SaveLanguageUseCase,
) : ViewModel() {
    fun saveLanguage(language: String) = viewModelScope.launch {
        saveLanguageUseCase(language = language)
    }
}