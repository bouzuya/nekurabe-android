package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemDetailViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("ItemDetail")
    val message: LiveData<String> = _message
}
