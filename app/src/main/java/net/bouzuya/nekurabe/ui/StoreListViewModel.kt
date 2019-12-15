package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Store
import net.bouzuya.nekurabe.data.StoreRepository

class StoreListViewModel(storeRepository: StoreRepository) : ViewModel() {
    private val _createEvent = MutableLiveData<Event<Unit>>()
    val createEvent: LiveData<Event<Unit>> = _createEvent

    private val _showEvent = MutableLiveData<Event<Store>>()
    val showEvent: LiveData<Event<Store>> = _showEvent

    private var _storeList = storeRepository.findAll()
    val storeList: LiveData<List<Store>> = _storeList

    fun create() {
        _createEvent.value = Event(Unit)
    }

    fun show(store: Store) {
        _showEvent.value = Event(store)
    }
}
