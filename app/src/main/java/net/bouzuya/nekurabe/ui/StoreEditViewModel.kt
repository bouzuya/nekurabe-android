package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Store
import net.bouzuya.nekurabe.data.StoreRepository
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class StoreEditViewModel(
    private val storeRepository: StoreRepository,
    private val storeId: Long
) : ViewModel() {

    private val _savedEvent = MutableLiveData<Event<Long>>()
    val savedEvent: LiveData<Event<Long>> = _savedEvent

    private val _store = MutableLiveData<Store>()

    val createdAt: LiveData<String> =
        Transformations.map(_store) {
            if (storeId == 0L) ""
            else DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt)
        }

    val id: LiveData<String> =
        Transformations.map(_store) {
            if (storeId == 0L) ""
            else it.id.toString(10)
        }

    // two-way binding
    val nameText = MutableLiveData<String>()

    val updatedAt: LiveData<String> =
        Transformations.map(_store) {
            if (storeId == 0L) ""
            else DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt)
        }

    init {
        viewModelScope.launch {
            if (storeId != 0L) {
                storeRepository.findById(storeId)?.let { store ->
                    _store.value = store
                    nameText.value = store.name
                }
            }
        }
    }

    fun save(): Job = viewModelScope.launch {
        val now = OffsetDateTime.now()
        val savedId = nameText.value?.let { name ->
            if (storeId == 0L) {
                val created = Store(0L, name, now, now)
                storeRepository.insert(created)
            } else {
                // update
                storeRepository.findById(storeId)?.let { store ->
                    val updated = store.copy(name = name, updatedAt = now)
                    storeRepository.update(updated)
                    store.id
                }
            }
        }
        savedId?.also { _savedEvent.value = Event(savedId) }
    }
}
