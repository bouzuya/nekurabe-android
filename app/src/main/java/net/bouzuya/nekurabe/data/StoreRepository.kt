package net.bouzuya.nekurabe.data

class StoreRepository(private val storeDao: StoreDao) {
    suspend fun findAll(): List<Store> = storeDao.findAll()

    suspend fun insert(store: Store): Unit = storeDao.insert(store)
}
