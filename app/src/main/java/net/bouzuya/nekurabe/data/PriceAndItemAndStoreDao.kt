package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PriceAndItemAndStoreDao {
    @Transaction
    @Query("SELECT * FROM prices ORDER BY id DESC")
    fun findAll(): LiveData<List<PriceAndItemAndStore>>

    @Transaction
    @Query("SELECT * FROM prices WHERE id = :priceId LIMIT 1")
    suspend fun findById(priceId: Long): PriceAndItemAndStore?
}
