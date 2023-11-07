package com.pokemon.core.domain.usecase.system

import com.pokemon.core.domain.repository.SystemRepository
import javax.inject.Inject

class SaveIsDarkThemeUseCase @Inject constructor(
    private val systemRepository: SystemRepository,
) {
    suspend operator fun invoke(isDarkTheme: Boolean) = kotlin.runCatching {
        systemRepository.saveIsDarkTheme(isDarkTheme = isDarkTheme)
    }
}