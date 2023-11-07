package com.pokemon.feature.main.home

import androidx.paging.PagingData
import com.pokemon.core.domain.entity.DetailGenerationEntity
import com.pokemon.core.domain.entity.DetailPokemonEntity
import com.pokemon.core.domain.entity.GenerationEntity
import com.pokemon.core.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val pokemonListPager: Flow<PagingData<PokemonEntity>>? = null,
    val pokemonList: Map<Int, String> = mapOf(),
    val typeList: Map<Int, String> = mapOf(),
    val generationListPager: Flow<PagingData<GenerationEntity>>? = null,
    val generationList: List<DetailGenerationEntity> = listOf(),
    val selectedGenerationList: List<DetailGenerationEntity> = listOf(),
    val selectedTypeList: List<String> = listOf(),
    val isDarkTheme: Boolean = false,
)