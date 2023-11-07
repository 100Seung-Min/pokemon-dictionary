package com.pokemon.core.domain.usecase.item

import com.pokemon.core.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemListUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        itemRepository.getItemList()
    }
}