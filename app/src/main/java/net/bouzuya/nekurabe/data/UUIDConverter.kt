package net.bouzuya.nekurabe.data

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {
    @TypeConverter
    fun fromString(s: String): UUID = UUID.fromString(s)

    @TypeConverter
    fun toString(uuid: UUID): String = uuid.toString()
}
