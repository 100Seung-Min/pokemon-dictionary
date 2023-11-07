package com.pokemon.core.remote.di

import com.pokemon.core.remote.datasource.evolution.EvolutionRemoteDataSource
import com.pokemon.core.remote.datasource.evolution.EvolutionRemoteDataSourceImpl
import com.pokemon.core.remote.datasource.item.ItemRemoteDataSource
import com.pokemon.core.remote.datasource.item.ItemRemoteDataSourceImpl
import com.pokemon.core.remote.datasource.move.MoveRemoteDataSource
import com.pokemon.core.remote.datasource.move.MoveRemoteDataSourceImpl
import com.pokemon.core.remote.datasource.pokemon.PokemonRemoteDataSource
import com.pokemon.core.remote.datasource.pokemon.PokemonRemoteDataSourceImpl
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
    abstract fun bindsItemRemoteDataSource(
        itemRemoteDataSourceImpl: ItemRemoteDataSourceImpl,
    ): ItemRemoteDataSource
}