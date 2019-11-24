package net.bouzuya.nekurabe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface StoreDao {
    @Query("SELECT * FROM stores")
    fun findAll(): LiveData<Store>
}
