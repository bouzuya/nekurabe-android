package net.bouzuya.nekurabe

import android.app.Application
import android.os.StrictMode
import androidx.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen
import net.bouzuya.nekurabe.data.ItemRepository
import net.bouzuya.nekurabe.data.NekurabeDatabase
import net.bouzuya.nekurabe.data.StoreRepository
import net.bouzuya.nekurabe.data.migration.NekurabeDatabaseMigration1to2
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
                        .build()
                }

                // dao
                single { get<NekurabeDatabase>().itemDao() }
                single { get<NekurabeDatabase>().storeDao() }

                // repository
                single { ItemRepository(get()) }
                single { StoreRepository(get()) }

                // view model
                factory { HomeViewModel() }
                factory { ItemListViewModel(get()) }
                factory { (storeId: Long) -> StoreDetailViewModel(get(), storeId) }
                factory { (storeId: Long) -> StoreEditViewModel(get(), storeId) }
                factory { StoreListViewModel(get()) }
            })
        }
    }
}
