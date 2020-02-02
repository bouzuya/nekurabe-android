package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.PriceAndItemAndStore
import net.bouzuya.nekurabe.data.PriceAndItemAndStoreRepository
import timber.log.Timber

class PriceListViewModel(
    priceAndItemAndStoreRepository: PriceAndItemAndStoreRepository
) : ViewModel() {
    val priceList: LiveData<List<PriceAndItemAndStore>> = priceAndItemAndStoreRepository.findAll()

    private val _newEvent = MutableLiveData<Event<Unit>>()
    val newEvent: LiveData<Event<Unit>> = _newEvent

    fun newPrice() {
        _newEvent.value = Event(Unit)
    }

    fun show(price: PriceAndItemAndStore) {
        Timber.d("%s", price)
        TODO()
    }
}
