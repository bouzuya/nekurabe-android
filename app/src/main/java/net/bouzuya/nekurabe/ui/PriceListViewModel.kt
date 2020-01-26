package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Price
import timber.log.Timber

class PriceListViewModel : ViewModel() {
    private val _priceList = MutableLiveData<List<Price>>(emptyList())
    val priceList: LiveData<List<Price>> = _priceList

    private val _newEvent = MutableLiveData<Event<Unit>>()
    val newEvent: LiveData<Event<Unit>> = _newEvent

    fun newPrice() {
        _newEvent.value = Event(Unit)
    }

    fun show(price: Price) {
        Timber.d("%s", price)
        TODO()
    }
}
