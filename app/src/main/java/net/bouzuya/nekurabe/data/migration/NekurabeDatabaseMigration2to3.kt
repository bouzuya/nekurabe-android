package net.bouzuya.nekurabe.data.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class NekurabeDatabaseMigration2to3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val tableName = "prices"
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `${tableName}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `itemId` INTEGER NOT NULL, `storeId` INTEGER NOT NULL, `price` INTEGER NOT NULL, `amount` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, FOREIGN KEY(`itemId`) REFERENCES `items`(`id`) ON UPDATE NO ACTION ON DELETE RESTRICT , FOREIGN KEY(`storeId`) REFERENCES `items`(`id`) ON UPDATE NO ACTION ON DELETE RESTRICT )"
        )
        database.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_prices_itemId` ON `${tableName}` (`itemId`)"
        )
        database.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_prices_storeId` ON `${tableName}` (`storeId`)"
        )
    }
}
