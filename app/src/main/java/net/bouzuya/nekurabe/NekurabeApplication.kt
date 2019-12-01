package net.bouzuya.nekurabe

import android.app.Application
import android.os.StrictMode
import androidx.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen
import net.bouzuya.nekurabe.data.NekurabeDatabase
import net.bouzuya.nekurabe.data.StoreRepository
import net.bouzuya.nekurabe.ui.HomeViewModel
import net.bouzuya.nekurabe.ui.StoreEditViewModel
import net.bouzuya.nekurabe.ui.StoreListViewModel
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
                single {
                    Room.databaseBuilder(
                        applicationContext,
                        NekurabeDatabase::class.java,
                        "nekurabe_database"
                    ).build()
                }
                single { get<NekurabeDatabase>().storeDao() }
                single { StoreRepository(get()) }
                factory { HomeViewModel() }
                factory { StoreEditViewModel(get()) }
                factory { StoreListViewModel(get()) }
            })
        }
    }
}
