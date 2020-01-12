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
    private val _editEvent = MutableLiveData<Event<Long>>()
    val editEvent: LiveData<Event<Long>> = _editEvent

    private val _item = MutableLiveData<Item>()

    val createdAt: LiveData<String> =
        Transformations.map(_item) { DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt) }

    val name: LiveData<String> = Transformations.map(_item) { it.name }

    val updatedAt: LiveData<String> =
        Transformations.map(_item) { DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt) }

    init {
        viewModelScope.launch {
            itemRepository.findById(itemId)?.let { item ->
                _item.value = item
            }
        }
    }

    fun edit() {
        _editEvent.value = Event(itemId)
    }
}
