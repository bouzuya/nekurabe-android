package net.bouzuya.nekurabe.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(
    tableName = "prices", foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = ["id"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Item::class,
            parentColumns = ["id"],
            childColumns = ["storeId"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.NO_ACTION
        )
    ]
)
data class Price(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    val itemId: Long,

    val storeId: Long,

    val price: Int,

    val amount: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: OffsetDateTime,

    @ColumnInfo(name = "updated_at")
    val updatedAt: OffsetDateTime
)
