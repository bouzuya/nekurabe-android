package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.*
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class PriceEditViewModel(
    private val itemRepository: ItemRepository,
    private val priceRepository: PriceRepository,
    private val storeRepository: StoreRepository,
    private val priceId: Long
) : ViewModel() {
    private val _savedEvent = MutableLiveData<Event<Long>>()
    val savedEvent: LiveData<Event<Long>> = _savedEvent

    private val _price = MutableLiveData<Price>()

    val createdAt: LiveData<String> =
        Transformations.map(_price) {
            if (priceId == 0L) ""
            else DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt)
        }

    // two-way binding
    val priceText = MutableLiveData<String>()

    // two-way binding
    val amountText = MutableLiveData<String>()

    val updatedAt: LiveData<String> =
        Transformations.map(_price) {
            if (priceId == 0L) ""
            else DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt)
        }

    init {
        viewModelScope.launch {
            if (priceId != 0L) {
                priceRepository.findById(priceId)?.let { item ->
                    _price.value = item
                    priceText.value = item.price.toString()
                    amountText.value = item.amount.toString()
                }
            }
        }
    }

    fun save(): Job = viewModelScope.launch {
        val now = OffsetDateTime.now()
        val savedId =
            priceText.value?.toIntOrNull()?.let { priceValue ->
                amountText.value?.toIntOrNull()?.let { amount ->
                    // FIXME: select item
                    itemRepository.findById(1L)?.let { item ->
                        // FIXME: select store
                        storeRepository.findById(1L)?.let { store ->
                            if (priceId == 0L) {
                                val created = Price(
                                    id = 0L,
                                    itemId = item.id,
                                    storeId = store.id,
                                    price = priceValue,
                                    amount = amount,
                                    createdAt = now,
                                    updatedAt = now
                                )
                                priceRepository.insert(created)
                            } else {
                                // update
                                priceRepository.findById(priceId)?.let { price ->
                                    val updated = price.copy(
                                        itemId = price.id,
                                        storeId = store.id,
                                        price = priceValue,
                                        amount = amount,
                                        updatedAt = now
                                    )
                                    priceRepository.update(updated)
                                    price.id
                                }
                            }
                        }
                    }
                }
            }
        savedId?.also { _savedEvent.value = Event(savedId) }
    }
}
