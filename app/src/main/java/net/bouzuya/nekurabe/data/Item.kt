package net.bouzuya.nekurabe.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime
import java.util.*

@Entity(
    tableName = "items",
    indices = [
        Index(value = ["uuid"], unique = true)
    ]
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "created_at")
    val createdAt: OffsetDateTime,

    @ColumnInfo(name = "updated_at")
    val updatedAt: OffsetDateTime,

    @ColumnInfo(name = "uuid")
    val uuid: UUID
)
