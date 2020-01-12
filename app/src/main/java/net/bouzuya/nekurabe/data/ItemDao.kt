package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun findAll(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id = :itemId LIMIT 1")
    suspend fun findById(itemId: Long): Item?

    @Insert
    suspend fun insert(item: Item): Long
}
