package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Item
import net.bouzuya.nekurabe.data.ItemRepository

class ItemEditViewModel(
    private val itemRepository: ItemRepository,
    private val itemId: Long
) : ViewModel() {
    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> = _item

    init {
        viewModelScope.launch {
            // TODO: replace by ItemRepository#findById
            itemRepository.findAll().value?.find { it.id == itemId }?.let { item ->
                _item.value = item
            }
        }
    }
}
