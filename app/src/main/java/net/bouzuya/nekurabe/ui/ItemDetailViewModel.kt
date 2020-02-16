package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Item
import net.bouzuya.nekurabe.data.ItemRepository
import org.threeten.bp.format.DateTimeFormatter

class ItemDetailViewModel(
    private val itemRepository: ItemRepository,
    private val itemId: Long
) : ViewModel() {
    private val _deleteCompleteEvent = MutableLiveData<Event<Unit>>()
    val deleteCompleteEvent: LiveData<Event<Unit>> = _deleteCompleteEvent

    private val _editEvent = MutableLiveData<Event<Long>>()
    val editEvent: LiveData<Event<Long>> = _editEvent

    private val _item = MutableLiveData<Item>()

    val createdAt: LiveData<String> =
        _item.map { DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt) }

    val name: LiveData<String> = _item.map { it.name }

    val updatedAt: LiveData<String> =
        _item.map { DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt) }

    init {
        viewModelScope.launch {
            itemRepository.findById(itemId)?.let { item ->
                _item.value = item
            }
        }
    }

    fun destroy() {
        viewModelScope.launch {
            itemRepository.findById(itemId)?.let { item ->
                itemRepository.delete(item)
                _deleteCompleteEvent.value = Event(Unit)
            }
        }
    }

    fun edit() {
        _editEvent.value = Event(itemId)
    }
}
