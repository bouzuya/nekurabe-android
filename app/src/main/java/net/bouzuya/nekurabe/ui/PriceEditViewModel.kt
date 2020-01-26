package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PriceEditViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("PriceEdit")
    val message: LiveData<String> = _message
}
