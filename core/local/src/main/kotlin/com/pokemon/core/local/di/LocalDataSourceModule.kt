package com.pokemon.core.local.di

import com.pokemon.core.data.local.datasource.SystemLocalDataSource
import com.pokemon.core.local.datasource.SystemLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindsSystemLocalDataSource(
        systemLocalDataSourceImpl: SystemLocalDataSourceImpl,
    ): SystemLocalDataSource
}