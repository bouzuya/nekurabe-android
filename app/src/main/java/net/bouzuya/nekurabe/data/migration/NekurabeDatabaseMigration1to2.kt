package net.bouzuya.nekurabe.data.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class NekurabeDatabaseMigration1to2 : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val tableName = "items"
        database.execSQL("CREATE TABLE IF NOT EXISTS `${tableName}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL)")
    }
}
