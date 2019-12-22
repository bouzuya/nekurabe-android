package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData

class ItemRepository(private val itemDao: ItemDao) {
    fun findAll(): LiveData<List<Item>> = itemDao.findAll()
}
