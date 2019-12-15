package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData

class StoreRepository(private val storeDao: StoreDao) {
    suspend fun delete(store: Store): Unit = storeDao.delete(store)

    fun findAll(): LiveData<List<Store>> = storeDao.findAll()

    suspend fun findById(storeId: Long) = storeDao.findById(storeId)

    suspend fun insert(store: Store): Long = storeDao.insert(store)

    suspend fun update(store: Store): Unit = storeDao.update(store)
}
