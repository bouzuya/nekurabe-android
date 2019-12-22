package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Item
import net.bouzuya.nekurabe.data.ItemRepository

class ItemListViewModel(itemRepository: ItemRepository) : ViewModel() {
    val itemList: LiveData<List<Item>> = itemRepository.findAll()

}
