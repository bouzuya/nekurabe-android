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

    // two-way binding
    val nameText = MutableLiveData<String>()

    fun create(): Job = viewModelScope.launch {
        nameText.value?.let { name ->
            val now = OffsetDateTime.now()
            val store = Store(0L, name, now, now)
            storeRepository.insert(store)
        }
    }
}
