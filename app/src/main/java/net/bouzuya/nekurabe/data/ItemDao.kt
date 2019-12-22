package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun findAll(): LiveData<List<Item>>
}
