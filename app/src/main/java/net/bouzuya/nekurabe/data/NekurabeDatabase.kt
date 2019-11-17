package net.bouzuya.nekurabe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [OffsetDateTimeConverter::class])
@Database(entities = [Store::class], version = 1)
abstract class NekurabeDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao

    companion object {
        @Volatile
        private var database: NekurabeDatabase? = null

        fun getDatabase(context: Context): NekurabeDatabase {
            return database ?: synchronized(this) {
                database ?: Room.databaseBuilder(
                    context.applicationContext,
                    NekurabeDatabase::class.java,
                    "nekurabe_database"
                )
                    .build().also { db ->
                        database = db
                    }
            }
        }
    }
}
