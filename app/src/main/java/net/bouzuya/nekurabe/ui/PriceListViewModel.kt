package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.PriceAndItemAndStore
import net.bouzuya.nekurabe.data.PriceAndItemAndStoreRepository

class PriceListViewModel(
    private val priceAndItemAndStoreRepository: PriceAndItemAndStoreRepository
) : ViewModel() {
    val priceList: LiveData<List<PriceAndItemAndStore>> = priceAndItemAndStoreRepository.findAll()

    val priceListWithMinDiff: LiveData<List<Pair<PriceAndItemAndStore, String>>> =
        priceList.switchMap { list ->
            liveData {
                emit(list.map {
                    Pair(
                        it,
                        String.format(
                            "%+.2f",
                            priceAndItemAndStoreRepository.minUnitPriceByItem(
                                it.item
                            )?.let { minUnitPrice -> it.unitPriceAsDouble - minUnitPrice } ?: 0.0
                        )
                    )
                })
            }
        }

    val _newEvent = MutableLiveData<Event<Unit>>()
    val newEvent: LiveData<Event<Unit>> = _newEvent

    private val _showEvent = MutableLiveData<Event<PriceAndItemAndStore>>()
    val showEvent: LiveData<Event<PriceAndItemAndStore>> = _showEvent

    fun newPrice() {
        _newEvent.value = Event(Unit)
    }

    fun show(price: PriceAndItemAndStore) {
        _showEvent.value = Event(price)
    }
}
