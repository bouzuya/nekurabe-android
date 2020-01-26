package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Price
import timber.log.Timber

class PriceListViewModel : ViewModel() {
    private val _priceList = MutableLiveData<List<Price>>(emptyList())
    val priceList: LiveData<List<Price>> = _priceList

    fun newPrice() {
        TODO()
    }

    fun show(price: Price) {
        Timber.d("%s", price)
        TODO()
    }
}
