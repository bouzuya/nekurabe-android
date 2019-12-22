package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemListViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("Item List")
    val message: LiveData<String> = _message
}
