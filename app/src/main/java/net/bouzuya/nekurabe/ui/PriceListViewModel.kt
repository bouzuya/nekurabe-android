package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.Price
import net.bouzuya.nekurabe.data.PriceRepository
import timber.log.Timber

class PriceListViewModel(
    priceRepository: PriceRepository
) : ViewModel() {
    val priceList: LiveData<List<Price>> = priceRepository.findAll()

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
