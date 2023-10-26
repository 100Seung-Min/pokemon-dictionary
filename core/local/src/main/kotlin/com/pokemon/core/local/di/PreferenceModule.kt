package com.pokemon.core.local.di

import com.pokemon.core.local.preference.SystemPreference
import com.pokemon.core.local.preference.SystemPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
    @Binds
    abstract fun bindsSystemPreference(
        systemPreferenceImpl: SystemPreferenceImpl,
    ): SystemPreference
}