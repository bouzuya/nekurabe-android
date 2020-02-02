package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.*
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class PriceEditViewModel(
    itemRepository: ItemRepository,
    private val priceRepository: PriceRepository,
    storeRepository: StoreRepository,
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

    private val itemList: LiveData<List<Item>> = itemRepository.findAll()

    private val storeList: LiveData<List<Store>> = storeRepository.findAll()

    val itemNameList: LiveData<List<String>> =
        Transformations.map(itemList) { list -> list.map { it.name } }

    // two-way binding
    val priceText = MutableLiveData<String>()

    // two-way binding
    val selectedItemPosition: MutableLiveData<Int> = MediatorLiveData<Int>().also { data ->
        data.addSource(itemList) { listOrNull ->
            listOrNull?.let { list ->
                _price.value?.itemId?.let { itemId ->
                    list.indexOfFirst { it.id == itemId }
                }
            }?.let { data.value = it }
        }
        data.addSource(_price) { priceOrNull ->
            itemList.value?.let { list ->
                priceOrNull?.itemId?.let { itemId ->
                    list.indexOfFirst { it.id == itemId }
                }
            }?.let { data.value = it }
        }
    }

    // two-way binding
    val selectedStorePosition: MutableLiveData<Int> = MediatorLiveData<Int>().also { data ->
        data.addSource(storeList) { listOrNull ->
            listOrNull?.let { list ->
                _price.value?.storeId?.let { storeId ->
                    list.indexOfFirst { it.id == storeId }
                }
            }?.let { data.value = it }
        }
        data.addSource(_price) { priceOrNull ->
            storeList.value?.let { list ->
                priceOrNull?.storeId?.let { storeId ->
                    list.indexOfFirst { it.id == storeId }
                }
            }?.let { data.value = it }
        }
    }

    val storeNameList: LiveData<List<String>> =
        Transformations.map(storeList) { list -> list.map { it.name } }

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
                priceRepository.findById(priceId)?.let { price ->
                    _price.value = price
                    priceText.value = price.price.toString()
                    amountText.value = price.amount.toString()
                }
            }
        }
    }

    fun save(): Job = viewModelScope.launch {
        val now = OffsetDateTime.now()
        val savedId =
            priceText.value?.toIntOrNull()?.let { priceValue ->
                amountText.value?.toIntOrNull()?.let { amount ->
                    selectedItemPosition.value?.let { itemList.value?.getOrNull(it) }
                        ?.let { item ->
                            selectedStorePosition.value?.let { storeList.value?.getOrNull(it) }
                                ?.let { store ->
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
                                                itemId = item.id,
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
