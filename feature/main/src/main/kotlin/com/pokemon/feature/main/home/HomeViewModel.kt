package com.pokemon.feature.main.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pokemon.core.design_system.util.Language
import com.pokemon.core.design_system.util.changeLanguage
import com.pokemon.core.domain.entity.DetailGenerationEntity
import com.pokemon.core.domain.entity.GenerationEntity
import com.pokemon.core.domain.usecase.generation.GetGenerationDetailUseCase
import com.pokemon.core.domain.usecase.generation.GetGenerationListUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonDetailUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonListUseCase
import com.pokemon.core.domain.usecase.system.FetchIsDarkThemeUseCase
import com.pokemon.core.domain.usecase.system.FetchLanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
    private val fetchLanguageUseCase: FetchLanguageUseCase,
    private val getGenerationListUseCase: GetGenerationListUseCase,
    private val getGenerationDetailUseCase: GetGenerationDetailUseCase,
    private val fetchIsDarkThemeUseCase: FetchIsDarkThemeUseCase,
) : ContainerHost<HomeState, Unit>, ViewModel() {
    override val container = container<HomeState, Unit>(HomeState())

    init {
        getInitList()
    }

    fun settingLanguage(context: Context) = intent {
        viewModelScope.launch {
            fetchLanguageUseCase().onSuccess {
                Language.values().toList().forEach { language ->
                    if (language.type == it) language.changeLanguage(context = context)
                }
            }
        }
    }

    private fun getInitList() = intent {
        viewModelScope.launch {
            getPokemonListUseCase().onSuccess {
                reduce { state.copy(pokemonListPager = it.cachedIn(viewModelScope)) }
            }
            getGenerationListUseCase().onSuccess {
                reduce { state.copy(generationListPager = it.cachedIn(viewModelScope)) }
            }
            fetchIsDarkThemeUseCase().onSuccess {
                reduce { state.copy(isDarkTheme = it) }
            }
        }
    }

    fun getPokemonInfo(pokemonId: Int) = intent {
        viewModelScope.launch {
            getPokemonInfoUseCase(pokemonId = pokemonId).onSuccess {
                reduce { state.copy(typeList = state.typeList.plus(pokemonId to it.typeList[0])) }
                getPokemonDetail(speciesId = it.speciesId, pokemonId = pokemonId)
            }
        }
    }

    private fun getPokemonDetail(speciesId: Int, pokemonId: Int) = intent {
        viewModelScope.launch {
            getPokemonDetailUseCase(pokemonId = speciesId).onSuccess {
                reduce { state.copy(pokemonList = state.pokemonList.plus(pokemonId to it.name)) }
            }
        }
    }

    fun getGenerationDetail(generationList: List<GenerationEntity?>) = intent {
        viewModelScope.launch {
            reduce { state.copy(generationList = listOf()) }
            generationList.forEach {
                it?.let {
                    getGenerationDetailUseCase(generationId = it.id).onSuccess {
                        reduce { state.copy(generationList = state.generationList.plus(it)) }
                    }
                }
            }
            reduce { state.copy(generationList = state.generationList.sortedBy { it.name }) }
        }
    }

    fun changeSelectGenerationList(selectGeneration: DetailGenerationEntity) = intent {
        if (state.selectedGenerationList.contains(selectGeneration)) {
            reduce {
                state.copy(selectedGenerationList = state.selectedGenerationList.filter { it.name != selectGeneration.name })
            }
        } else {
            reduce {
                state.copy(
                    selectedGenerationList = state.selectedGenerationList.plus(selectGeneration)
                        .sortedBy { it.name })
            }
        }
    }

    fun changeSelectTypeList(selectType: String) = intent {
        if (state.selectedTypeList.contains(selectType)) {
            reduce { state.copy(selectedTypeList = state.selectedTypeList.filter { it != selectType }) }
        } else {
            reduce { state.copy(selectedTypeList = state.selectedTypeList.plus(selectType)) }
        }
    }
}