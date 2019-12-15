package net.bouzuya.nekurabe.data

import androidx.room.*

@Dao
interface StoreDao {
    @Delete
    suspend fun delete(store: Store)

    @Query("SELECT * FROM stores")
    suspend fun findAll(): List<Store>

    @Query("SELECT * FROM stores WHERE id = :storeId LIMIT 1")
    suspend fun findById(storeId: Long): Store?

    @Insert
    suspend fun insert(store: Store): Long

    @Update
    suspend fun update(store: Store)
}
