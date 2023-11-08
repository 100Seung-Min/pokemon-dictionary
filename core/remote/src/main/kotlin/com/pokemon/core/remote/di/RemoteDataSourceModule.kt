package com.pokemon.core.remote.di

import com.pokemon.core.data.remote.datasource.EvolutionRemoteDataSource
import com.pokemon.core.remote.datasource.EvolutionRemoteDataSourceImpl
import com.pokemon.core.data.remote.datasource.GenerationRemoteDataSource
import com.pokemon.core.remote.datasource.GenerationRemoteDataSourceImpl
import com.pokemon.core.data.remote.datasource.ItemRemoteDataSource
import com.pokemon.core.remote.datasource.ItemRemoteDataSourceImpl
import com.pokemon.core.data.remote.datasource.MoveRemoteDataSource
import com.pokemon.core.remote.datasource.MoveRemoteDataSourceImpl
import com.pokemon.core.data.remote.datasource.PokemonRemoteDataSource
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

    @Binds
    abstract fun bindsMoveRemoteDataSource(
        moveRemoteDataSourceImpl: MoveRemoteDataSourceImpl,
    ): MoveRemoteDataSource

    @Binds
    abstract fun bindsEvolutionRemoteDataSource(
        evolutionRemoteDataSourceImpl: EvolutionRemoteDataSourceImpl,
    ): EvolutionRemoteDataSource

    @Binds
    abstract fun bindsGenerationRemoteDataSource(
        generationRemoteDataSourceImpl: GenerationRemoteDataSourceImpl,
    ): GenerationRemoteDataSource

    @Binds
    abstract fun bindsItemRemoteDataSource(
        itemRemoteDataSourceImpl: ItemRemoteDataSourceImpl,
    ): ItemRemoteDataSource
}