package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Event

class HomeViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("HomeViewModel")
    val message: LiveData<String> = _message

    private val _navigateToStoreListEvent = MutableLiveData<Event<Unit>>()
    val navigateStoreListEvent: LiveData<Event<Unit>> = _navigateToStoreListEvent

    fun navigateToStoreList() {
        _navigateToStoreListEvent.value = Event(Unit)
    }
}
