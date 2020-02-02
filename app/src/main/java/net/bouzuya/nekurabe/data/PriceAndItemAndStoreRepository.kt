package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData

class PriceAndItemAndStoreRepository(
    private val priceDao: PriceDao,
    private val priceAndItemAndStoreDao: PriceAndItemAndStoreDao
) {
    suspend fun delete(entity: PriceAndItemAndStore): Unit = priceDao.delete(entity.price)

    fun findAll(): LiveData<List<PriceAndItemAndStore>> = priceAndItemAndStoreDao.findAll()

    suspend fun findById(priceId: Long): PriceAndItemAndStore? =
        priceAndItemAndStoreDao.findById(priceId)

    suspend fun insert(price: PriceAndItemAndStore): Long = priceDao.insert(price.price)

    suspend fun update(price: PriceAndItemAndStore): Unit = priceDao.update(price.price)
}
