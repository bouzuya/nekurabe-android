package net.bouzuya.nekurabe.data.migration

import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.threetenabp.AndroidThreeTen
import net.bouzuya.nekurabe.data.NekurabeDatabase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NekurabeDatabaseMigration3to4Test {

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
    fun migrate3to4() {
        val databaseName = "test-database"

        migrationTestHelper.createDatabase(databaseName, 3).also { database ->
            testStoresTable(database)
            testItemsTableV1(database)
            testPricesTable(database)
        }

        migrationTestHelper.runMigrationsAndValidate(
            databaseName,
            4,
            true,
            NekurabeDatabaseMigration3to4()
        ).also { database ->
            testItemsTableV4(database)
        }
    }
}
