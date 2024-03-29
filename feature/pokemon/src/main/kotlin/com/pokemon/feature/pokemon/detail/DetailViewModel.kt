package com.pokemon.feature.pokemon.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemon.core.domain.usecase.evolution.GetEvolutionInfoUseCase
import com.pokemon.core.domain.usecase.move.GetMoveDetailUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonDetailUseCase
import com.pokemon.core.domain.usecase.pokemon.GetPokemonInfoUseCase
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
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
    private val getMoveDetailUseCase: GetMoveDetailUseCase,
    private val getEvolutionInfoUseCase: GetEvolutionInfoUseCase,
) : ContainerHost<DetailState, Unit>, ViewModel() {
    override val container = container<DetailState, Unit>(DetailState())

    suspend fun getPokemonInfo(pokemonId: Int, finishLoading: () -> Unit) = intent {
        viewModelScope.launch {
            getPokemonInfoUseCase(pokemonId = pokemonId).onSuccess {
                reduce {
                    state.copy(
                        profileUrl = it.profileUrl,
                        weight = it.weight,
                        height = it.height,
                        typeList = it.typeList
                    )
                }
                it.moveList.forEach {
                    getMoveDetail(moveId = it)
                }
                getPokemonDetail(pokemonId = it.speciesId, finishLoading = finishLoading)
            }
        }
    }

    private suspend fun getPokemonDetail(pokemonId: Int, finishLoading: () -> Unit) = intent {
        viewModelScope.launch {
            getPokemonDetailUseCase(pokemonId = pokemonId).onSuccess {
                reduce {
                    state.copy(
                        id = it.id,
                        englishName = it.englishName,
                        name = it.name,
                        genus = it.genus,
                        flavorList = it.flavorList
                    )
                }
                getEvolutionInfo(evolutionId = it.evolutionId, finishLoading = finishLoading)
            }
        }
    }


    private suspend fun getMoveDetail(moveId: Int) = intent {
        viewModelScope.launch {
            getMoveDetailUseCase(moveId = moveId).onSuccess {
                reduce {
                    state.copy(
                        moveList = state.moveList.plus(it)
                    )
                }
            }
        }
    }

    private suspend fun getEvolutionInfo(evolutionId: Int, finishLoading: () -> Unit) = intent {
        viewModelScope.launch {
            getEvolutionInfoUseCase(evolutionId = evolutionId).onSuccess {
                reduce { state.copy(evolutionList = it.evolutionList) }
                finishLoading()
            }
        }
    }
}