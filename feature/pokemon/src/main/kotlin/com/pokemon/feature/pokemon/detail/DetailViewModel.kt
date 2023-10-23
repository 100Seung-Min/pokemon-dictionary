package com.pokemon.feature.pokemon.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.core.domain.usecase.pokemon.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) : ContainerHost<DetailState, Unit>, ViewModel() {
    override val container = container<DetailState, Unit>(DetailState())

    suspend fun getPokemonDetail(pokemonId: Int) = intent {
        viewModelScope.launch {
            getPokemonDetailUseCase(pokemonId = pokemonId).onSuccess {
                reduce {
                    state.copy(
                        id = it.id,
                        profileUrl = it.profileUrl,
                        name = it.name,
                        genus = it.genus,
                        flavorList = it.flavorList
                    )
                }
            }
        }
    }
}