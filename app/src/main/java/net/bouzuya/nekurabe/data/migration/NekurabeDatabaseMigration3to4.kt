package net.bouzuya.nekurabe.data.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import net.bouzuya.nekurabe.data.UUIDConverter
import java.util.*

class NekurabeDatabaseMigration3to4 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val tableName = "items"

        database.execSQL("ALTER TABLE `${tableName}` ADD COLUMN `uuid` TEXT") // NOT NULL

        val ids = mutableListOf<Long>()
        database.query("SELECT id FROM `${tableName}`").also { cursor ->
            while (cursor.moveToNext()) {
                ids.add(cursor.getLong(0))
            }
        }
        ids.forEach { id ->
            database.execSQL(
                "UPDATE `${tableName}` SET uuid = ? WHERE id = ?",
                arrayOf(UUIDConverter().toString(UUID.randomUUID()), id)
            )
        }

        // The 12-step generalized ALTER TABLE procedure
        // https://www.sqlite.org/lang_altertable.html#making_other_kinds_of_table_schema_changes
        database.execSQL("CREATE TABLE IF NOT EXISTS `new_${tableName}` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, `uuid` TEXT NOT NULL)")
        database.execSQL("INSERT INTO `new_${tableName}` SELECT * FROM `${tableName}`")
        database.execSQL("DROP TABLE `${tableName}`")
        database.execSQL("ALTER TABLE `new_${tableName}` RENAME TO `${tableName}`")
        database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_items_uuid` ON `${tableName}` (`uuid`)")
    }
}
