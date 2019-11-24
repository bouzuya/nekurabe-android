package net.bouzuya.nekurabe.data

import androidx.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId

class OffsetDateTimeConverter {
    @TypeConverter
    fun fromLong(l: Long): OffsetDateTime =
        OffsetDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneId.systemDefault())

    @TypeConverter
    fun toLong(dt: OffsetDateTime): Long =
        dt.toInstant().toEpochMilli()
}
