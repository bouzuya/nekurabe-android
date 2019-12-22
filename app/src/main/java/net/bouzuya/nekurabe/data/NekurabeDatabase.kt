package net.bouzuya.nekurabe.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [OffsetDateTimeConverter::class])
@Database(entities = [Item::class, Store::class], version = 2)
abstract class NekurabeDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}
