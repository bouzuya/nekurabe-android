package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Item
import net.bouzuya.nekurabe.data.ItemRepository

class ItemListViewModel(itemRepository: ItemRepository) : ViewModel() {
    val itemList: LiveData<List<Item>> = itemRepository.findAll()

    private val _newEvent = MutableLiveData<Event<Unit>>()
    val newEvent: LiveData<Event<Unit>> = _newEvent

    private val _showEvent = MutableLiveData<Event<Item>>()
    val showEvent: LiveData<Event<Item>> = _showEvent

    fun newItem() {
        _newEvent.value = Event(Unit)
    }

    fun show(item: Item) {
        _showEvent.value = Event(item)
    }
}
