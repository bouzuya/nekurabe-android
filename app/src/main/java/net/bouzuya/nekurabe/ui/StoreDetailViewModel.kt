package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Store
import net.bouzuya.nekurabe.data.StoreRepository
import org.threeten.bp.format.DateTimeFormatter

class StoreDetailViewModel(
    private val storeRepository: StoreRepository,
    private val storeId: Long
) : ViewModel() {
    private val _deleteCompleteEvent = MutableLiveData<Event<Unit>>()
    val deleteCompleteEvent: LiveData<Event<Unit>> = _deleteCompleteEvent

    private val _editEvent = MutableLiveData<Event<Long>>()
    val editEvent: LiveData<Event<Long>> = _editEvent

    private val _store = MutableLiveData<Store>()

    val createdAt: LiveData<String> =
        _store.map { DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt) }

    val name: LiveData<String> = _store.map { it.name }

    val updatedAt: LiveData<String> =
        _store.map { DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt) }

    init {
        viewModelScope.launch {
            storeRepository.findById(storeId)?.let { store ->
                _store.value = store
            }
        }
    }

    fun destroy() {
        viewModelScope.launch {
            storeRepository.findById(storeId)?.let { store ->
                storeRepository.delete(store)
                _deleteCompleteEvent.value = Event(Unit)
            }
        }
    }

    fun edit() {
        _editEvent.value = Event(storeId)
    }
}
