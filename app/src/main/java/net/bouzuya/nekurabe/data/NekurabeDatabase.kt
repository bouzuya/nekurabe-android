package net.bouzuya.nekurabe.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [OffsetDateTimeConverter::class])
@Database(entities = [Item::class, Price::class, Store::class], version = 3)
abstract class NekurabeDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun storeDao(): StoreDao
}
