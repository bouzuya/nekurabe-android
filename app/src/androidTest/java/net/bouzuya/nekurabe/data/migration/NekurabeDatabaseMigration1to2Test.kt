package net.bouzuya.nekurabe.data.migration

import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.threetenabp.AndroidThreeTen
import net.bouzuya.nekurabe.data.NekurabeDatabase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NekurabeDatabaseMigration1to2Test {

    @get:Rule
    val migrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        NekurabeDatabase::class.java.canonicalName
    )

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        AndroidThreeTen.init(context)
    }

    @Test
    fun migrate1to2() {
        val databaseName = "test-database"

        migrationTestHelper.createDatabase(databaseName, 1).also { database ->
            testStoresTable(database)
        }

        migrationTestHelper.runMigrationsAndValidate(
            databaseName,
            2,
            true,
            NekurabeDatabaseMigration1to2()
        ).also { database ->
            testItemsTable(database)
        }
    }
}
