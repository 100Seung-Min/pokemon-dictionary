package com.pokemon.feature.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.core.domain.usecase.pokemon.GetPokemonListUseCase
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
) : ContainerHost<HomeState, Unit>, ViewModel() {
    override val container = container<HomeState, Unit>(HomeState())

    init {
        getPokemonList()
    }

    private fun getPokemonList() = intent {
        viewModelScope.launch {
            getPokemonListUseCase().onSuccess {
                reduce { state.copy(pokemonListPager = it) }
            }.onFailure {
            }
        }
    }
}