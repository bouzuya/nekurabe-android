package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StoreEditViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("StoreEdit")
    val message: LiveData<String> = _message
}
