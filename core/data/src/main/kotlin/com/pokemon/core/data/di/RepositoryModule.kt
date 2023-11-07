package com.pokemon.core.data.di

import com.pokemon.core.data.repository.ItemRepositoryImpl
import com.pokemon.core.data.repository.PokemonRepositoryImpl
import com.pokemon.core.data.repository.SystemRepositoryImpl
import com.pokemon.core.domain.repository.ItemRepository
import com.pokemon.core.domain.repository.PokemonRepository
import com.pokemon.core.domain.repository.SystemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl,
    ): PokemonRepository

    @Binds
    abstract fun bindsSystemRepository(
        systemRepositoryImpl: SystemRepositoryImpl,
    ): SystemRepository

    @Binds
    abstract fun bindsItemRepository(
        itemRepositoryImpl: ItemRepositoryImpl,
    ): ItemRepository
}