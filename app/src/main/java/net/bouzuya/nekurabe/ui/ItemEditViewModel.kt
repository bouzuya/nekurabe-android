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

    val createdAt: LiveData<String> =
        Transformations.map(_item) {
            if (itemId == 0L) ""
            else DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt)
        }

    // two-way binding
    val nameText = MutableLiveData<String>()

    val updatedAt: LiveData<String> =
        Transformations.map(_item) {
            if (itemId == 0L) ""
            else DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt)
        }

    init {
        viewModelScope.launch {
            if (itemId != 0L) {
                // TODO: replace by ItemRepository#findById
                itemRepository.findAll().value?.find { it.id == itemId }?.let { item ->
                    _item.value = item
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
                TODO("Edit Item")
            }
        }
        savedId?.also { _savedEvent.value = Event(savedId) }
    }
}
