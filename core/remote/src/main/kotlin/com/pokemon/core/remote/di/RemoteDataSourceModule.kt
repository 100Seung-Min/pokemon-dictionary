package com.pokemon.core.remote.di

import com.pokemon.core.remote.datasource.PokemonRemoteDataSource
import com.pokemon.core.remote.datasource.PokemonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindsPokemonRemoteDataSource(
        pokemonRemoteDataSourceImpl: PokemonRemoteDataSourceImpl,
    ): PokemonRemoteDataSource
}