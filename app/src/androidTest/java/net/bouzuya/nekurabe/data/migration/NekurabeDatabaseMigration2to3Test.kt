package net.bouzuya.nekurabe.data.migration

import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.threetenabp.AndroidThreeTen
import net.bouzuya.nekurabe.data.NekurabeDatabase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NekurabeDatabaseMigration2to3Test {

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
    fun migrate2to3() {
        val databaseName = "test-database"

        migrationTestHelper.createDatabase(databaseName, 2).also { database ->
            testStoresTable(database)
            testItemsTableV1(database)
        }

        migrationTestHelper.runMigrationsAndValidate(
            databaseName,
            3,
            true,
            NekurabeDatabaseMigration2to3()
        ).also { database ->
            testPricesTable(database)
        }
    }
}
