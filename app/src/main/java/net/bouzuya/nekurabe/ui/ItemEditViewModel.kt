package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Item
import net.bouzuya.nekurabe.data.ItemRepository
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class ItemEditViewModel(
    private val itemRepository: ItemRepository,
    private val itemId: Long
) : ViewModel() {

    private val _savedEvent = MutableLiveData<Event<Long>>()
    val savedEvent: LiveData<Event<Long>> = _savedEvent

    private val _item = MutableLiveData<Item>()

    val createdAt: LiveData<String> = _item.map {
        if (itemId == 0L) ""
        else DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt)
    }

    // two-way binding
    val nameText = MutableLiveData<String>()

    val updatedAt: LiveData<String> = _item.map {
        if (itemId == 0L) ""
        else DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt)
    }

    init {
        viewModelScope.launch {
            if (itemId != 0L) {
                itemRepository.findById(itemId)?.let { item ->
                    _item.value = item
                    nameText.value = item.name
                }
            }
        }
    }

    fun save(): Job = viewModelScope.launch {
        val now = OffsetDateTime.now()
        val savedId = nameText.value?.let { name ->
            if (itemId == 0L) {
                val created = Item(0L, name, now, now)
                itemRepository.insert(created)
            } else {
                // update
                itemRepository.findById(itemId)?.let { item ->
                    val updated = item.copy(name = name, updatedAt = now)
                    itemRepository.update(updated)
                    item.id
                }
            }
        }
        savedId?.also { _savedEvent.value = Event(savedId) }
    }
}
