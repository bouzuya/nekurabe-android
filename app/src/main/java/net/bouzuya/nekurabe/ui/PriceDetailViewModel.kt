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

    private val _editEvent = MutableLiveData<Event<Long>>()
    val editEvent: LiveData<Event<Long>> = _editEvent

    private val _price = MutableLiveData<PriceAndItemAndStore>()

    val createdAt: LiveData<String> =
        Transformations.map(_price) {
            DateTimeFormatter.ISO_DATE_TIME.format(it.price.createdAt)
        }

    val itemName: LiveData<String> = Transformations.map(_price) { it.item.name }

    val priceText: LiveData<String> = Transformations.map(_price) { it.price.price.toString() }

    val storeName: LiveData<String> = Transformations.map(_price) { it.store.name }

    val amountText: LiveData<String> = Transformations.map(_price) { it.price.amount.toString() }

    val updatedAt: LiveData<String> =
        Transformations.map(_price) {
            DateTimeFormatter.ISO_DATE_TIME.format(it.price.updatedAt)
        }

    init {
        viewModelScope.launch {
            priceAndItemAndStoreRepository.findById(priceId)?.let { price ->
                _price.value = price
            }
        }
    }

    fun edit() {
        _editEvent.value = Event(priceId)
    }
}
