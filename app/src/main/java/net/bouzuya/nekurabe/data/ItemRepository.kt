package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData

class ItemRepository(private val itemDao: ItemDao) {
    suspend fun delete(item: Item): Unit = itemDao.delete(item)

    fun findAll(): LiveData<List<Item>> = itemDao.findAll()

    suspend fun findById(itemId: Long): Item? = itemDao.findById(itemId)

    suspend fun insert(item: Item): Long = itemDao.insert(item)

    suspend fun update(item: Item): Unit = itemDao.update(item)
}
