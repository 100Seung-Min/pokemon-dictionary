package com.pokemon.feature.item.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pokemon.core.domain.usecase.pokemon.GetItemListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
) : ViewModel(), ContainerHost<ItemState, Unit> {
    override val container = container<ItemState, Unit>(ItemState())

    fun getItemList() = intent {
        viewModelScope.launch {
            getItemListUseCase().onSuccess {
                reduce { state.copy(itemList = it.cachedIn(viewModelScope)) }
            }
        }
    }
}