package net.bouzuya.nekurabe.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class HistoryRepository(private val context: Context) {
    private fun getLongOrNull(key: String): Long? = sharedPreferences().let { prefs ->
        if (prefs.contains(key)) prefs.getLong(key, 0L)
        else null
    }

    private fun sharedPreferences(): SharedPreferences =
        PreferenceManager(context).sharedPreferences

    private val latestStoreIdKey = "latest_store_id"

    fun getLatestStoreId(): Long? = getLongOrNull(latestStoreIdKey)

    fun setLatestStoreId(storeId: Long): Unit = sharedPreferences().edit(true) {
        putLong(latestStoreIdKey, storeId)
    }
}
