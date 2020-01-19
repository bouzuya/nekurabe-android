package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PriceListViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("Price List")
    val message: LiveData<String> = _message
}
