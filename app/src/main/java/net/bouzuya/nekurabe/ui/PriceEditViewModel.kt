package net.bouzuya.nekurabe.ui

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.*
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

class PriceEditViewModel(
    private val historyRepository: HistoryRepository,
    itemRepository: ItemRepository,
    private val priceRepository: PriceRepository,
    storeRepository: StoreRepository,
    private val priceId: Long
) : ViewModel() {
    private val _savedEvent = MutableLiveData<Event<Long>>()
    val savedEvent: LiveData<Event<Long>> = _savedEvent

    private val _price = MutableLiveData<Price>()

    val createdAt: LiveData<String> = _price.map {
        if (priceId == 0L) ""
        else DateTimeFormatter.ISO_DATE_TIME.format(it.createdAt)
    }

    private val itemList: LiveData<List<Item>> = itemRepository.findAll()

    private val storeList: LiveData<List<Store>> = storeRepository.findAll()

    val itemNameList: LiveData<List<String>> = itemList.map { list -> list.map { it.name } }

    // two-way binding
    val priceText = MutableLiveData<String>()

    // two-way binding
    val selectedItemPosition: MutableLiveData<Int> = MediatorLiveData<Int>().also { data ->
        fun updatePosition(listOrNull: List<Item>?, itemIdOrNull: Long?) {
            listOrNull?.let { l -> itemIdOrNull?.let { i -> l.indexOfFirst { it.id == i } } }
                ?.let { data.value = it }
        }
        data.addSource(itemList) { l -> updatePosition(l, _price.value?.itemId) }
        data.addSource(_price) { p -> updatePosition(itemList.value, p?.itemId) }
    }

    // two-way binding
    val selectedStorePosition: MutableLiveData<Int> =
        MediatorLiveData<Int>().also { data ->
            fun updatePosition(listOrNull: List<Store>?, storeIdOrNull: Long?) {
                listOrNull?.let { l -> storeIdOrNull?.let { i -> l.indexOfFirst { it.id == i } } }
                    ?.let { data.value = it }
            }
            data.addSource(storeList) { l -> updatePosition(l, _price.value?.storeId) }
            data.addSource(_price) { p -> updatePosition(storeList.value, p?.storeId) }
        }

    val storeNameList: LiveData<List<String>> = storeList.map { list -> list.map { it.name } }

    // two-way binding
    val amountText = MutableLiveData<String>()

    // two-way binding
    val includingTax = MutableLiveData<Boolean>(false)

    val updatedAt: LiveData<String> = _price.map {
        if (priceId == 0L) ""
        else DateTimeFormatter.ISO_DATE_TIME.format(it.updatedAt)
    }

    private fun <T> LiveData<T>.observeOnce(fn: (t: T) -> Unit) {
        observeForever(object : Observer<T> {
            override fun onChanged(t: T) {
                removeObserver(this)
                fn(t)
            }
        })
    }

    init {
        viewModelScope.launch {
            if (priceId == 0L) {
                selectedStorePosition.observeOnce {
                    historyRepository.getLatestStoreId()?.let { latestStoreId ->
                        storeList.value?.indexOfFirst { it.id == latestStoreId }
                    }?.let { selectedStorePosition.value = it }
                }
            } else {
                priceRepository.findById(priceId)?.let { price ->
                    _price.value = price
                    // selectedItemPosition
                    // selectedStorePosition
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
                includingTax.value?.let { it -> priceValue * 100 / (100 + (if (it) 10 else 0)) }
            }?.let { priceValue ->
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
                                        historyRepository.setLatestStoreId(store.id)
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
