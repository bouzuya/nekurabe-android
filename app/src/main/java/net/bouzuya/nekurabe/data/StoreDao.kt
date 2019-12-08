package net.bouzuya.nekurabe.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StoreDao {
    @Query("SELECT * FROM stores")
    suspend fun findAll(): List<Store>

    @Insert
    suspend fun insert(store: Store)

    @Query("SELECT * FROM stores WHERE id = :storeId LIMIT 1")
    suspend fun findById(storeId: Long): Store?
}
