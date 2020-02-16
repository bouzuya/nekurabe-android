package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Event
import net.bouzuya.nekurabe.data.PriceAndItemAndStore
import net.bouzuya.nekurabe.data.PriceAndItemAndStoreRepository
import org.threeten.bp.format.DateTimeFormatter

class PriceDetailViewModel(
    private val priceAndItemAndStoreRepository: PriceAndItemAndStoreRepository,
    private val priceId: Long
) : ViewModel() {
    private val _deleteCompleteEvent = MutableLiveData<Event<Unit>>()
    val deleteCompleteEvent: LiveData<Event<Unit>> = _deleteCompleteEvent

    private val _editEvent = MutableLiveData<Event<Long>>()
    val editEvent: LiveData<Event<Long>> = _editEvent

    private val _price = MutableLiveData<PriceAndItemAndStore>()

    val createdAt: LiveData<String> = _price.map {
        DateTimeFormatter.ISO_DATE_TIME.format(it.price.createdAt)
    }

    val itemName: LiveData<String> = _price.map { it.item.name }

    val priceText: LiveData<String> = _price.map { it.price.price.toString() }

    val storeName: LiveData<String> = _price.map { it.store.name }

    val amountText: LiveData<String> = _price.map { it.price.amount.toString() }

    val updatedAt: LiveData<String> = _price.map {
        DateTimeFormatter.ISO_DATE_TIME.format(it.price.updatedAt)
    }

    init {
        viewModelScope.launch {
            priceAndItemAndStoreRepository.findById(priceId)?.let { price ->
                _price.value = price
            }
        }
    }

    fun destroy() {
        viewModelScope.launch {
            priceAndItemAndStoreRepository.findById(priceId)?.let { price ->
                priceAndItemAndStoreRepository.delete(price)
                _deleteCompleteEvent.value = Event(Unit)
            }
        }
    }

    fun edit() {
        _editEvent.value = Event(priceId)
    }
}
