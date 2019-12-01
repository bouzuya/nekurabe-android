package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Store
import net.bouzuya.nekurabe.data.StoreRepository

class StoreListViewModel(private val storeRepository: StoreRepository) : ViewModel() {
    private val _storeList = MutableLiveData<List<Store>>()
    val storeList: LiveData<List<Store>> = _storeList

    init {
        viewModelScope.launch {
            _storeList.value = storeRepository.findAll()
        }
    }

    fun create() {
        TODO()
    }
}
