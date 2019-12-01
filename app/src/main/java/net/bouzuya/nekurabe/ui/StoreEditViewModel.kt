package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.bouzuya.nekurabe.data.Store
import net.bouzuya.nekurabe.data.StoreRepository
import org.threeten.bp.OffsetDateTime

class StoreEditViewModel(private val storeRepository: StoreRepository) : ViewModel() {
    private val _message = MutableLiveData<String>("StoreEdit")
    val message: LiveData<String> = _message

    fun create(): Job = viewModelScope.launch {
        val now = OffsetDateTime.now()
        val store = Store(0L, "name1", now, now) // FIXME: name
        storeRepository.insert(store)
    }
}
