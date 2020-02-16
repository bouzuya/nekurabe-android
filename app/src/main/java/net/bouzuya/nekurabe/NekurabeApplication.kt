package net.bouzuya.nekurabe

import android.app.Application
import android.os.StrictMode
import androidx.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen
import net.bouzuya.nekurabe.data.*
import net.bouzuya.nekurabe.data.migration.NekurabeDatabaseMigration1to2
import net.bouzuya.nekurabe.data.migration.NekurabeDatabaseMigration2to3
import net.bouzuya.nekurabe.ui.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class NekurabeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(applicationContext)
            modules(module {
                // database
                single {
                    Room.databaseBuilder(
                        applicationContext,
                        NekurabeDatabase::class.java,
                        "nekurabe_database"
                    )
                        .addMigrations(NekurabeDatabaseMigration1to2())
                        .addMigrations(NekurabeDatabaseMigration2to3())
                        .build()
                }

                // dao
                single { get<NekurabeDatabase>().itemDao() }
                single { get<NekurabeDatabase>().priceAndItemAndStoreDao() }
                single { get<NekurabeDatabase>().priceDao() }
                single { get<NekurabeDatabase>().storeDao() }

                // repository
                single { HistoryRepository(applicationContext) }
                single { ItemRepository(get()) }
                single { PriceAndItemAndStoreRepository(get(), get()) }
                single { PriceRepository(get()) }
                single { StoreRepository(get()) }

                // view model
                factory { HomeViewModel() }
                factory { (itemId: Long) -> ItemDetailViewModel(get(), itemId) }
                factory { (itemId: Long) -> ItemEditViewModel(get(), itemId) }
                factory { ItemListViewModel(get()) }
                factory { (priceId: Long) -> PriceDetailViewModel(get(), priceId) }
                factory { (priceId: Long) ->
                    PriceEditViewModel(
                        get(),
                        get(),
                        get(),
                        get(),
                        priceId
                    )
                }
                factory { PriceListViewModel(get()) }
                factory { (storeId: Long) -> StoreDetailViewModel(get(), storeId) }
                factory { (storeId: Long) -> StoreEditViewModel(get(), storeId) }
                factory { StoreListViewModel(get()) }
            })
        }
    }
}
