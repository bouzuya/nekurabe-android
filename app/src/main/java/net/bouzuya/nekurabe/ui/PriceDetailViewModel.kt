package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PriceDetailViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("PriceDetailViewModel")
    val message: LiveData<String> = _message
}
