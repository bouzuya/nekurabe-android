package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PriceDao {
    @Delete
    suspend fun delete(price: Price)

    @Query("SELECT * FROM prices")
    fun findAll(): LiveData<List<Price>>

    @Query("SELECT * FROM prices WHERE id = :priceId LIMIT 1")
    suspend fun findById(priceId: Long): Price?

    @Insert
    suspend fun insert(price: Price): Long

    @Update
    suspend fun update(price: Price)
}
