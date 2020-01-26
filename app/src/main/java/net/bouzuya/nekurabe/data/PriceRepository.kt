package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData

class PriceRepository(private val priceDao: PriceDao) {
    suspend fun delete(price: Price): Unit = priceDao.delete(price)

    fun findAll(): LiveData<List<Price>> = priceDao.findAll()

    suspend fun findById(priceId: Long) = priceDao.findById(priceId)

    suspend fun insert(price: Price): Long = priceDao.insert(price)

    suspend fun update(price: Price): Unit = priceDao.update(price)
}
