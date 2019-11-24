package net.bouzuya.nekurabe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bouzuya.nekurabe.data.Store
import org.threeten.bp.OffsetDateTime

class StoreListViewModel : ViewModel() {
    // TODO:
    private val _storeList = MutableLiveData<List<Store>>(
        listOf(
            Store(1, "name1", OffsetDateTime.now(), OffsetDateTime.now()),
            Store(2, "name2", OffsetDateTime.now(), OffsetDateTime.now()),
            Store(3, "name3", OffsetDateTime.now(), OffsetDateTime.now())
        )
    )
    val storeList: LiveData<List<Store>> = _storeList

}
