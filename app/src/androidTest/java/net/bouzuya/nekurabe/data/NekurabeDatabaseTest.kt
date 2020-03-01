package net.bouzuya.nekurabe.data

import androidx.room.testing.MigrationTestHelper
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.threetenabp.AndroidThreeTen
import net.bouzuya.nekurabe.data.migration.testItemsTableV1
import net.bouzuya.nekurabe.data.migration.testItemsTableV4
import net.bouzuya.nekurabe.data.migration.testPricesTable
import net.bouzuya.nekurabe.data.migration.testStoresTable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NekurabeDatabaseTest {
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
    fun createDatabaseVersion1() {
        migrationTestHelper.createDatabase("test-database", 1).also { database ->
            testStoresTable(database)
        }
    }

    @Test
    fun createDatabaseVersion2() {
        migrationTestHelper.createDatabase("test-database", 2).also { database ->
            testItemsTableV1(database)
            testStoresTable(database)
        }
    }

    @Test
    fun createDatabaseVersion3() {
        migrationTestHelper.createDatabase("test-database", 3).also { database ->
            testItemsTableV1(database)
            testStoresTable(database)
            testPricesTable(database)
        }
    }

    @Test
    fun createDatabaseVersion4() {
        migrationTestHelper.createDatabase("test-database", 4).also { database ->
            testItemsTableV4(database)
            testStoresTable(database)
            testPricesTable(database)
        }
    }
}
