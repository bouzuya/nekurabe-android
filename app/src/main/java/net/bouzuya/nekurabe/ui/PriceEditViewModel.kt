package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.PriceRepository

class PriceEditViewModel(
    private val priceRepository: PriceRepository,
    private val priceId: Long
) : ViewModel() {
    private val _message = MutableLiveData<String>("PriceEdit")
    val message: LiveData<String> = _message
}
