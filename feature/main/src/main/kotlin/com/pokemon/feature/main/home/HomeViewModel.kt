package com.pokemon.feature.main.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pokemon.core.design_system.util.Language
import com.pokemon.core.design_system.util.changeLanguage
import com.pokemon.core.domain.usecase.pokemon.GetPokemonDetailUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonInfoUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonListUseCase
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
) : ContainerHost<HomeState, Unit>, ViewModel() {
    override val container = container<HomeState, Unit>(HomeState())

    init {
        getPokemonList()
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

    private fun getPokemonList() = intent {
        viewModelScope.launch {
            getPokemonListUseCase().onSuccess {
                reduce { state.copy(pokemonListPager = it.cachedIn(viewModelScope)) }
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
}