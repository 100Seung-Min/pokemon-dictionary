package com.pokemon.feature.main.home

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val pokemonListPager: Flow<PagingData<PokemonEntity>>? = null,
    val pokemonList: Map<Int, String> = mapOf(),
    val typeList: Map<Int, String> = mapOf(),
)