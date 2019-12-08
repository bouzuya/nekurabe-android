package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Store
import net.bouzuya.nekurabe.data.StoreRepository

class StoreListViewModel(private val storeRepository: StoreRepository) : ViewModel() {
    private val _createEvent = MutableLiveData<Event<Unit>>()
    val createEvent: LiveData<Event<Unit>> = _createEvent

    private val _showEvent = MutableLiveData<Event<Store>>()
    val showEvent: LiveData<Event<Store>> = _showEvent

    private val _storeList = MutableLiveData<List<Store>>()
    val storeList: LiveData<List<Store>> = _storeList

    init {
        viewModelScope.launch {
            _storeList.value = storeRepository.findAll()
        }
    }

    fun create() {
        _createEvent.value = Event(Unit)
    }

    fun show(store: Store) {
        _showEvent.value = Event(store)
    }
}
